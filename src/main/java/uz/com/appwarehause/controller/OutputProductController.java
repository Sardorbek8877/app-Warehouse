package uz.com.appwarehause.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.com.appwarehause.entity.OutputProduct;
import uz.com.appwarehause.payload.OutputProductDto;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/outProduct")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;

    //ADD OUTPUT PRODUCT
    @PostMapping
    public Result addOutputProduct(@RequestBody OutputProductDto outputProductDto){
        return outputProductService.addOutputProduct(outputProductDto);
    }

    //GET OUTPUT PRODUCTS
    @GetMapping
    public List<OutputProduct> getOutputProducts(){
        return outputProductService.getOutputProducts();
    }

    //GET OUTPUT PRODUCT BY ID
    public OutputProduct getOutputProductById(@PathVariable Integer id){
        return outputProductService.getOutputProductById(id);
    }

    //DELETE OUTPUT PRODUCT
    public Result deleteOutputProduct(@PathVariable Integer id){
        return outputProductService.deleteOutputProduct(id);
    }

    //EDIT OUTPUT PRODUCT
    public Result editOutputProduct(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto){
        return outputProductService.editOutputProduct(id, outputProductDto);
    }
}
