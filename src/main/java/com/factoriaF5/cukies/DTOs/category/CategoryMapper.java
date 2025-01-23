package com.factoriaF5.cukies.DTOs.category;

import com.factoriaF5.cukies.model.Category;

public class CategoryMapper {
    public static Category dtoToEntity(CategoryDTORequest categoryDTORequest){
        return new Category(categoryDTORequest.name());
    }
    public static CategoryDTOResponse entityToDTO(Category category){
        return new CategoryDTOResponse(
                category.getId(),
                category.getName()
        );
    }
}
