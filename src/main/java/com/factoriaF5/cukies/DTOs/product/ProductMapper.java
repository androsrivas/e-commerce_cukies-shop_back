package com.factoriaF5.cukies.DTOs.product;

import com.factoriaF5.cukies.model.Product;

public class ProductMapper {
    public static Product toEntity(ProductDTO productDTO){
        return new Product(
                productDTO.name(),
                productDTO.price(),
                productDTO.imageUrl(),
                productDTO.featured()
        );
    }
    public static ProductDTO toproductDTO(Product product){
        return new ProductDTO(
                product.getName(),
                product.getPrice(),
                product.getImageUrl(),
                product.isFeatured()
        );
    }
}
