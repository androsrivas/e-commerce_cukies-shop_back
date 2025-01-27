package com.factoriaF5.cukies.DTOs.product;

import com.factoriaF5.cukies.DTOs.category.CategoryMapper;

import com.factoriaF5.cukies.model.Category;
import com.factoriaF5.cukies.model.Product;
import com.factoriaF5.cukies.repository.CategoryRepository;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDetailDTOResponse toDetailDTOResponse (Product product);
    ProductSummaryDTOResponse toSummaryDTOResponse (Product product);
    Product fromSummaryDTOToEntity (ProductSummaryDTORequest productSummaryDTORequest);
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
