package uz.com.appwarehause.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.com.appwarehause.entity.InputProduct;
import uz.com.appwarehause.payload.InputProductDto;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

    // ADD INPUT PRODUCT
    @PostMapping
    public Result addInputProduct(@RequestBody InputProductDto inputProductDto){
        return inputProductService.addInputProduct(inputProductDto);
    }

    // GET INPUT PRODUCTS
    @GetMapping
    public List<InputProduct> getInputProduct(){
        return inputProductService.getInputProducts();
    }

    // GET INPUT PRODUCT BY ID
    public InputProduct getIPById(@PathVariable Integer id){
        return inputProductService.getIPById(id);
    }

    //DELETE INPUT PRODUCT
    @DeleteMapping("/{id}")
    public Result deleteIP(@RequestBody Integer id){
        return inputProductService.deleteInputProduct(id);
    }

    // EDIT INPUT PRODUCT
    @PutMapping("/{id}")
    public Result editInputProduct(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto){
        return inputProductService.editInputProduct(id, inputProductDto);
    }
}
