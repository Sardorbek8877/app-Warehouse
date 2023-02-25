package uz.com.appwarehause.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.com.appwarehause.entity.Product;
import uz.com.appwarehause.payload.ProductDto;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    //ADD PRODUCT
    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }

    //GET PRODUCTS
    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    //GET PRODUCT BY ID
    public Product getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    //DELETE PRODUCT
    public Result deleteProduct(@PathVariable Integer id){
        return productService.deleteProduct(id);
    }

    //EDIT PRODUCT
    public Result editProduct(@PathVariable Integer id, @RequestBody ProductDto productDto){
        return productService.editProduct(id, productDto);
    }
}