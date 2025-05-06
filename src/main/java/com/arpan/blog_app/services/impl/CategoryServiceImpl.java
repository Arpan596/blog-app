package com.arpan.blog_app.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


import com.arpan.blog_app.entities.Category;
import com.arpan.blog_app.exceptions.ResourceNotFoundException;
import com.arpan.blog_app.payloads.CategoryDto;
import com.arpan.blog_app.repositories.CategoryRepo;
import com.arpan.blog_app.services.CategoryService;

public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cate = this.modelMapper.map(categoryDto, Category.class);
        Category addedCategory = this.categoryRepo.save(cate);
        return this.modelMapper.map(addedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

        Category cate = this.categoryRepo.findById(categoryId)
        .orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId ));
        cate.setCategoryType(categoryDto.getCategoryType());
        cate.setCategoyDescription(categoryDto.getCategoyDescription());

        Category updated = this.categoryRepo.save(cate);

        return this.modelMapper.map(updated, CategoryDto.class);
       
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        
        Category cate = this.categoryRepo.findById(categoryId)
        .orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId ));
        this.categoryRepo.delete(cate);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {

        Category cate = this.categoryRepo.findById(categoryId)
        .orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId ));
        
    }

    @Override
    public List<CategoryDto> getAllCategory(CategoryDto categorDto) {
       
    }

}
