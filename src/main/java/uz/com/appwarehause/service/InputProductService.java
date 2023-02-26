package uz.com.appwarehause.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.com.appwarehause.entity.Input;
import uz.com.appwarehause.entity.InputProduct;
import uz.com.appwarehause.entity.Product;
import uz.com.appwarehause.payload.InputProductDto;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.repository.InputProductRepository;
import uz.com.appwarehause.repository.InputRepository;
import uz.com.appwarehause.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    ProductRepository productRepository;

    public Result addInputProduct(@RequestBody InputProductDto inputProductDto){

        //CHECK INPUT
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInput.isEmpty())
            return new Result("Input not found", false);

        //CHECK PRODUCT
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (optionalProduct.isEmpty())
            return new Result("Product not found", false);

        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProductRepository.save(inputProduct);
        return new Result("Input product added", true);
    }

    public List<InputProduct> getInputProducts(){
        return inputProductRepository.findAll();
    }

    public InputProduct getIPById(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isEmpty())
            return new InputProduct();
        return optionalInputProduct.get();
    }

    public Result deleteInputProduct(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isPresent()){
            inputProductRepository.deleteById(id);
            return new Result("InputProduct deleted", true);
        }
        return new Result("InputProduct not found", false);
    }

    public Result editInputProduct(Integer id, InputProductDto inputProductDto){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isEmpty())
            return new Result("InputProduct not found", false);

        //CHECK INPUT
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInput.isEmpty())
            return new Result("Input not found", false);

        //CHECK PRODUCT
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (optionalProduct.isEmpty())
            return new Result("Product not found", false);

        InputProduct editingInputProduct = optionalInputProduct.get();

        editingInputProduct.setProduct(optionalProduct.get());
        editingInputProduct.setInput(optionalInput.get());
        editingInputProduct.setAmount(inputProductDto.getAmount());
        editingInputProduct.setPrice(inputProductDto.getPrice());
        editingInputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProductRepository.save(editingInputProduct);
        return new Result("Input product edited", true);
    }
}
