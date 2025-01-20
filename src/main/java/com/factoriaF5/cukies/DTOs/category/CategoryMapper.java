package com.factoriaF5.cukies.DTOs.category;

import com.factoriaF5.cukies.model.Category;

import java.util.Optional;

public class CategoryMapper {
    public static Category dtoToEntity(CategoryDTO categoryDTO){
        return new Category(categoryDTO.name());
    }
    public static CategoryDTO entityToDTO(Category category){
        return new CategoryDTO(category.getName());
    }
}
