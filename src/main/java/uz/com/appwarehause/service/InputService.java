package uz.com.appwarehause.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.com.appwarehause.entity.*;
import uz.com.appwarehause.payload.InputDto;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.repository.CurrencyRepository;
import uz.com.appwarehause.repository.InputRepository;
import uz.com.appwarehause.repository.SupplierRepository;
import uz.com.appwarehause.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public Result addInput(InputDto inputDto){

        //CHECK WAREHOUSE
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty())
            return new Result("Warehouse not found", false);
        Warehouse warehouse = optionalWarehouse.get();

        //SUPPLIER CHECK
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (optionalSupplier.isEmpty())
            return new Result("Supplier not found", false);
        Supplier supplier = optionalSupplier.get();

        //CURRENCY CHECK
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalCurrency.isEmpty())
            return new Result("Currency not found", false);
        Currency currency = optionalCurrency.get();

        Input input = new Input();
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setFactureNumber(generateCode());
        input.setWarehouse(warehouse);
        input.setSupplier(supplier);
        input.setCurrency(currency);
        inputRepository.save(input);
        return new Result("Input added", true);
    }

    public List<Input> getInputs(){
        return inputRepository.findAll();
    }

    public Input getInputById(@PathVariable Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isEmpty())
            return new Input();
        return optionalInput.get();
    }

    public Result deleteInput(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isEmpty())
            return new Result("Input not found", false);
        inputRepository.deleteById(id);
        return new Result("Input deleted", true);
    }

    public Result editInput(Integer id, InputDto inputDto){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isEmpty())
            return new Result("Input not found", false);

        //CHECK WAREHOUSE
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty())
            return new Result("Warehouse not found", false);
        Warehouse warehouse = optionalWarehouse.get();

        //SUPPLIER CHECK
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (optionalSupplier.isEmpty())
            return new Result("Supplier not found", false);
        Supplier supplier = optionalSupplier.get();

        //CURRENCY CHECK
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalCurrency.isEmpty())
            return new Result("Currency not found", false);
        Currency currency = optionalCurrency.get();

        Input input = optionalInput.get();
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setWarehouse(warehouse);
        input.setSupplier(supplier);
        input.setCurrency(currency);
        inputRepository.save(input);
        return new Result("Input edited", true);
    }











    private String generateCode(){
        List<Input> inputList = inputRepository.findAll();
        return String.valueOf(inputList.size());
    }
}










