package com.factoriaF5.cukies.DTOs.product;

import com.factoriaF5.cukies.DTOs.image.ImageDTO;
import com.factoriaF5.cukies.model.Image;
import com.factoriaF5.cukies.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.name", target = "categoryName")
//    @Mapping(source = "image", target = "image")
    ProductDTOResponse toDTOResponse(Product product);

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(target = "image", ignore = true)
    Product toEntity(ProductDTORequest productDTORequest);

//    default ImageDTO mapImages(Image image) {
//        return new ImageDTO(image.getName(), null, image.getUrl());
//    }
//
//    default Image toImageEntity(ImageDTO imageDTO) {
//        if (imageDTO != null && imageDTO.getFile() != null) {
//            Image image = new Image();
//            image.setName(image.getName());
//            return image;
//        }
//
//        return null;
//    }

}
