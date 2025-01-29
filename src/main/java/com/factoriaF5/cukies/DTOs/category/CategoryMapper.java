package com.factoriaF5.cukies.DTOs.category;

import com.factoriaF5.cukies.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTOResponse toDTOResponse(Category category);
    Category toEntity(CategoryDTORequest categoryDTO);

//    public static Category dtoToEntity(CategoryDTORequest categoryDTORequest){
//        return new Category(categoryDTORequest.name());
//    }
//    public static CategoryDTOResponse entityToDTO(Category category){
//        return new CategoryDTOResponse(
//                category.getName()
//        );
//    }
}
