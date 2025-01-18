package com.factoriaF5.cukies.service;

import com.factoriaF5.cukies.DTOs.category.CategoryDTO;
import com.factoriaF5.cukies.DTOs.category.CategoryMapper;
import com.factoriaF5.cukies.exception.EmptyException;
import com.factoriaF5.cukies.model.Category;
import com.factoriaF5.cukies.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Category newCategory = CategoryMapper.dtoToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(newCategory);
        return CategoryMapper.entityToDTO(savedCategory);
    }
    public List<CategoryDTO> getCategories(){
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) throw new EmptyException();
        List<CategoryDTO> listCategories = categories.stream().map(category -> CategoryMapper.entityToDTO(category)).toList();
        return listCategories;
    }
}
