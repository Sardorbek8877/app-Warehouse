package uz.com.appwarehause.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.com.appwarehause.entity.Output;
import uz.com.appwarehause.entity.OutputProduct;
import uz.com.appwarehause.entity.Product;
import uz.com.appwarehause.payload.OutputProductDto;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.repository.OutputProductRepository;
import uz.com.appwarehause.repository.OutputRepository;
import uz.com.appwarehause.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;

    public Result addOutputProduct(OutputProductDto outputProductDto){
        //CHECK PRODUCT
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (optionalProduct.isEmpty())
            return new Result("Product not found", false);

        //CHECK OUTPUT
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (optionalOutput.isEmpty())
            return new Result("Output not found", false);

        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Result("OutputProduct added", true);
    }

    public List<OutputProduct> getOutputProducts(){
        return outputProductRepository.findAll();
    }

    public OutputProduct getOutputProductById(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isEmpty())
            return new OutputProduct();
        return optionalOutputProduct.get();
    }

    public Result deleteOutputProduct(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isPresent()){
            outputProductRepository.deleteById(id);
            return new Result("OutputProduct deleted", true);
        }
        return new Result("OutputProduct not found", false);
    }

    public Result editOutputProduct(Integer id, OutputProductDto outputProductDto){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isEmpty())
            return new Result("OutputProduct not found", false);

        //CHECK PRODUCT
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (optionalProduct.isEmpty())
            return new Result("Product not found", false);

        //CHECK OUTPUT
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (optionalOutput.isEmpty())
            return new Result("Output not found", false);

        OutputProduct outputProduct = optionalOutputProduct.get();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());

        outputProductRepository.save(outputProduct);
        return new Result("OutputProduct edited", true);
    }
}
