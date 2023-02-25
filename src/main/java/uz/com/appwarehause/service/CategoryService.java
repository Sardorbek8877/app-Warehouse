package uz.com.appwarehause.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.com.appwarehause.entity.Category;
import uz.com.appwarehause.payload.CategoryDto;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null){
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (optionalParentCategory.isEmpty())
                return new Result("Category not found!", false);
            category.setParentCategory(optionalParentCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Category successfully added!", true);
    }

    public List<Category> getCategories(){
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    public Category getCategoryById(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())
            return new Category();
        Category category = optionalCategory.get();
        return category;
    }

    public Result deleteCategory(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            categoryRepository.deleteById(id);
            return new Result("Category successfully deleted!", true);
        }
        return new Result("Category not founded", false);
    }

    public Result editCategory(Integer id, CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())
            return new Result("Category not found", false);

        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());

        if (categoryDto.getParentCategoryId() != null){
            Optional<Category> categoryOptional = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (categoryOptional.isEmpty()){
                return new Result("Parent category not found!", false);
            }
            category.setParentCategory(categoryOptional.get());
            categoryRepository.save(category);
            return new Result("Category edited!", true);
        }
        return new Result("Parent category dont exist!", false);
    }
}



