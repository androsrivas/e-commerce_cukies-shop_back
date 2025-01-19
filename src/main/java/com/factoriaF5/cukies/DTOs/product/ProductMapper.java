package com.factoriaF5.cukies.DTOs.product;

import com.factoriaF5.cukies.model.Product;

public class ProductMapper {
    public static Product dtoToEntity(ProductDTO productDTO){
        return new Product(
                productDTO.name(),
                productDTO.price(),
                productDTO.imageUrl(),
                productDTO.featured()
        );
    }
    public static ProductDTO entityToDTO(Product product){
        return new ProductDTO(
                product.getName(),
                product.getPrice(),
                product.getImageUrl(),
                product.isFeatured()
        );
    }
}
