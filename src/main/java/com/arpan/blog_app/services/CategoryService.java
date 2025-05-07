package com.arpan.blog_app.services;

import java.util.List;

import com.arpan.blog_app.payloads.CategoryDto;

public interface CategoryService {

    //create
    CategoryDto createCategory(CategoryDto categoryDto);
    //update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    //delete
    public void deleteCategory(Integer categoryId);
    //get
     CategoryDto getCategory(Integer categoryId);
    //get all users
    List<CategoryDto> getAllCategory(CategoryDto categorDto);

}
