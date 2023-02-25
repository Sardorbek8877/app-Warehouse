package uz.com.appwarehause.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.com.appwarehause.entity.Category;
import uz.com.appwarehause.payload.CategoryDto;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    CategoryService categoryService;

    //ADD CATEGORY
    @PostMapping
    public Result addCategory(CategoryDto categoryDto){
        return categoryService.addCategory(categoryDto);
    }

    //GET CATEGORIES
    @GetMapping
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    //GET CATEGORY BY ID
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id){
        return categoryService.getCategoryById(id);
    }

    //DELETE CATEGORY
    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id){
        return categoryService.deleteCategory(id);
    }

    //EDIT CATEGORY
    @PutMapping("/{id}")
    public Result editCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        return categoryService.editCategory(id, categoryDto);
    }
}








