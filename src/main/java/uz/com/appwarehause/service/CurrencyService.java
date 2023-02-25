package uz.com.appwarehause.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.com.appwarehause.entity.Currency;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public Result addCurrency(Currency currency){
        boolean exist = currencyRepository.existsByName(currency.getName());
        if (exist)
            return new Result("Currency already exist", false);

        currencyRepository.save(currency);
        return new Result("Currency added", true);
    }

    public List<Currency> getCurrencies(){
        return currencyRepository.findAll();
    }

    public Currency getCurrencyById(Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isEmpty())
            return new Currency();
        return optionalCurrency.get();
    }

    public Result deleteCurrency(Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()){
            currencyRepository.deleteById(id);
            return new Result("Currency deleted", true);
        }
        return new Result("Currency not found", false);
    }

    public Result editCurrency(Integer id, Currency currency){
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        if (currencyOptional.isEmpty())
            return new Result("Currency not found", false);
        Currency editingCurrency = currencyOptional.get();
        editingCurrency.setName(currency.getName());
        currencyRepository.save(editingCurrency);
        return new Result("Currency edited", true);
    }
}
