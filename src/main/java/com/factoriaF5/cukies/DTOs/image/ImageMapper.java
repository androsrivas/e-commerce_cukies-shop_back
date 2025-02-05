package com.factoriaF5.cukies.DTOs.image;

import com.factoriaF5.cukies.model.Image;
import com.factoriaF5.cukies.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    Image toEntity(ImageDTO imageDTO);
    ImageDTO toDTO(Image image);

}
