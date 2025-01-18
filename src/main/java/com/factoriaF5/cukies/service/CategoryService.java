package com.factoriaF5.cukies.service;

import com.factoriaF5.cukies.DTOs.category.CategoryDTO;
import com.factoriaF5.cukies.DTOs.category.CategoryMapper;
import com.factoriaF5.cukies.model.Category;
import com.factoriaF5.cukies.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public CategoryDTO saveCategory(CategoryDTO categoryDTO){
        Category newCategory = CategoryMapper.dtoToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(newCategory);
        return CategoryMapper.entityToDTO(savedCategory);
    }
}
