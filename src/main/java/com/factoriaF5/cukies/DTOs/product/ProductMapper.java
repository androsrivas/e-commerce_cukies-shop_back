package com.factoriaF5.cukies.DTOs.product;

import com.factoriaF5.cukies.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.name", target = "categoryName")
    ProductDTOResponse toDTOResponse(Product product);

    Product toEntity(ProductDTORequest productDTORequest);

//    public static Product dtoToEntity(ProductSummaryDTORequest productSummaryDTORequest, CategoryRepository categoryRepository){
//        Category category = categoryRepository.findById(productSummaryDTORequest.categoryId())
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//
//        return new Product(
//                productSummaryDTORequest.name(),
//                productSummaryDTORequest.price(),
//                productSummaryDTORequest.imageUrl(),
//                productSummaryDTORequest.featured(),
//                category
//        );
//    }
//    public static ProductSummaryDTORequest entityToDTO(Product product){
//
//        return new ProductSummaryDTORequest(
//                product.getName(),
//                product.getPrice(),
//                product.getImageUrl(),
//                product.isFeatured(),
//                CategoryMapper.entityToDTO(product.getCategory()).id()
//        );
//    }
}
