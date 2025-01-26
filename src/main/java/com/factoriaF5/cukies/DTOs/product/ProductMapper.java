package com.factoriaF5.cukies.DTOs.product;


import com.factoriaF5.cukies.DTOs.category.CategoryDTOResponse;
import com.factoriaF5.cukies.DTOs.category.CategoryMapper;

import com.factoriaF5.cukies.model.Category;
import com.factoriaF5.cukies.model.Product;
import com.factoriaF5.cukies.repository.CategoryRepository;

import java.util.List;

public class ProductMapper {
    public static Product dtoToEntity(ProductDTO productDTO, CategoryRepository categoryRepository){
        Category category = categoryRepository.findById(productDTO.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return new Product(
                productDTO.name(),
                productDTO.price(),
                productDTO.imageUrl(),
                productDTO.featured(),
                category
        );
    }
    public static ProductDTO entityToDTO(Product product){

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl(),
                product.isFeatured(),
                CategoryMapper.entityToDTO(product.getCategory()).id(),
                product.getCustomers()
        );
    }
}
