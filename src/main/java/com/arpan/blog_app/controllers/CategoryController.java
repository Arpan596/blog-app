package com.arpan.blog_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpan.blog_app.payloads.ApiResponse;
import com.arpan.blog_app.payloads.CategoryDto;
import com.arpan.blog_app.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto   createCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED); 
    }

    //update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
        CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
    }
    
    //delete
    @DeleteMapping("/{categoryId}")
    public  ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted successfully",true),HttpStatus.OK);
    }

    //get
    @GetMapping("/{CategoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer CategoryId){

        CategoryDto cate = this.categoryService.getCategory(CategoryId);
        return ResponseEntity.ok(cate);
    }

    //getall
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory(CategoryDto categoryDto){

        List<CategoryDto> cat = this.categoryService.getAllCategory(categoryDto);
        return ResponseEntity.ok(cat); 
    } 


}
