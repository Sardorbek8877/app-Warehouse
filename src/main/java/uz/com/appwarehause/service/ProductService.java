package uz.com.appwarehause.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.com.appwarehause.entity.Attachment;
import uz.com.appwarehause.entity.Category;
import uz.com.appwarehause.entity.Measurement;
import uz.com.appwarehause.entity.Product;
import uz.com.appwarehause.payload.ProductDto;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.repository.AttachmentRepository;
import uz.com.appwarehause.repository.CategoryRepository;
import uz.com.appwarehause.repository.MeasurementRepository;
import uz.com.appwarehause.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    public Result addProduct(ProductDto productDto){

        boolean exists = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (exists)
            return new Result("This Product already exist in this category", false);

        //CHECK CATEGORY
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty())
            return new Result("Category not found!", false);

        //PHOTO CHECK
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (optionalAttachment.isEmpty())
            return new Result("Photo not found", false);

        //MEASUREMENT CHECK
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (optionalMeasurement.isEmpty())
            return new Result("Measurement not found!", false);

        //SAVE
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCode(generateCode());
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new Result("Product successfully added!", true);
    }



    private String generateCode(){
        List<Product> productList = productRepository.findAll();
        return String.valueOf(productList.size());
    }
}
