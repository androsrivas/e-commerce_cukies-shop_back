package com.factoriaF5.cukies.DTOs.product;

import com.factoriaF5.cukies.model.Product;
import com.factoriaF5.cukies.repository.CategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.name", target = "categoryName")
    ProductDTOResponse toDTOResponse(Product product);

    Product toEntity(ProductDTORequest productDTORequest);
}
