package com.factoriaF5.cukies.DTOs.product;


import com.factoriaF5.cukies.DTOs.category.CategoryMapper;

import com.factoriaF5.cukies.model.Category;
import com.factoriaF5.cukies.model.Product;

import java.util.List;

public class ProductMapper {
    public static Product dtoToEntity(ProductDTO productDTO){
        Category category = new Category();

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
                product.getName(),
                product.getPrice(),
                product.getImageUrl(),
                product.isFeatured(),
                CategoryMapper.entityToDTO(product.getCategory()),
                product.getCustomers()
        );
    }
}
