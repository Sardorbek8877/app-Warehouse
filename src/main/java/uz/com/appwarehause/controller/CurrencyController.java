package uz.com.appwarehause.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.com.appwarehause.entity.Currency;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    // ADD CURRENCY
    @PostMapping
    public Result addCurrency(Currency currency){
        return currencyService.addCurrency(currency);
    }

    // GET CURRENCY
    @GetMapping
    public List<Currency> getCurrencies(){
        return currencyService.getCurrencies();
    }

    // GET CURRENCY BY ID
    @GetMapping("/{id}")
    public Currency getCurrencyById(@PathVariable Integer id){
        return currencyService.getCurrencyById(id);
    }

    // DELETE CURRENCY
    @DeleteMapping("/{id}")
    public Result deleteCurrency(@PathVariable Integer id){
        return currencyService.deleteCurrency(id);
    }

    // EDIT CURRENCY
    @PutMapping("/{id}")
    public Result editCurrency(@PathVariable Integer id, @RequestBody Currency currency){
        return currencyService.editCurrency(id, currency);
    }
}
