package uz.com.appwarehause.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.com.appwarehause.entity.*;
import uz.com.appwarehause.payload.OutputDto;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public Result addOutput(OutputDto outputDto){
        //CHECK WAREHOUSE
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty())
            return new Result("Warehouse not found", false);
        Warehouse warehouse = optionalWarehouse.get();

        //CLIENT CHECK
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (optionalClient.isEmpty())
            return new Result("Client not found", false);
        Client client = optionalClient.get();

        //CURRENCY CHECK
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (optionalCurrency.isEmpty())
            return new Result("Currency not found", false);
        Currency currency = optionalCurrency.get();

        Output output = new Output();
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setCode(generateCode());
        output.setCurrency(currency);
        output.setClient(client);
        output.setWarehouse(warehouse);
        outputRepository.save(output);
        return new Result("Output added", true);
    }

    public List<Output> getOutputs(){
        return outputRepository.findAll();
    }

    public Output getOutputById(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty())
            return new Output();
        return optionalOutput.get();
    }

    public Result deleteOutput(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()){
            outputRepository.deleteById(id);
            return new Result("Output deleted", true);
        }
        return new Result("Output not found", false);
    }

    public Result editOutput(Integer id, OutputDto outputDto){

        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty())
            return new Result("Output not found", false);
        Output output = optionalOutput.get();

        //CHECK WAREHOUSE
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty())
            return new Result("Warehouse not found", false);
        Warehouse warehouse = optionalWarehouse.get();

        //CLIENT CHECK
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (optionalClient.isEmpty())
            return new Result("Client not found", false);
        Client client = optionalClient.get();

        //CURRENCY CHECK
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (optionalCurrency.isEmpty())
            return new Result("Currency not found", false);
        Currency currency = optionalCurrency.get();

        output.setFactureNumber(output.getFactureNumber());
        output.setWarehouse(warehouse);
        output.setClient(client);
        output.setCurrency(currency);
        outputRepository.save(output);
        return new Result("Output edited", true);
    }

    private String generateCode(){
        List<Output> outputList = outputRepository.findAll();
        return String.valueOf(outputList.size());
    }
}









