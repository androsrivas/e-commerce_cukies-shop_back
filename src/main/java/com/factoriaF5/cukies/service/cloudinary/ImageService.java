package com.factoriaF5.cukies.service.cloudinary;

import com.factoriaF5.cukies.DTOs.image.ImageDTO;
import com.factoriaF5.cukies.DTOs.image.ImageMapper;
import com.factoriaF5.cukies.exception.image.ImageUploadException;
import com.factoriaF5.cukies.exception.product.ProductNotFoundException;
import com.factoriaF5.cukies.model.Image;
import com.factoriaF5.cukies.model.Product;
import com.factoriaF5.cukies.repository.ImageRepository;
import com.factoriaF5.cukies.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;
    private final CloudinaryService cloudinaryService;
    private final ImageMapper imageMapper;

    public ImageService(ImageRepository imageRepository, ProductRepository productRepository, CloudinaryService cloudinaryService, ImageMapper imageMapper) {
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
        this.cloudinaryService = cloudinaryService;
        this.imageMapper = imageMapper;
    }

    @Transactional
    public ResponseEntity<Map> uploadImage(ImageDTO imageModel) {
        try {
            Product product = productRepository.findById(imageModel.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("ID", imageModel.getProductId()));

            Image image = imageMapper.toEntity(imageModel);
            image.setUrl(cloudinaryService.uploadImage(imageModel.getFile(), "folder_1"));
            if (image.getUrl() == null) {
                throw new ImageUploadException(imageModel.getName(), "No se ha podido subir la imagen al servidor.");
            }
            image.setProduct(product);
            imageRepository.save(image);

            ImageDTO responseDTO = imageMapper.toDTO(image);
            return ResponseEntity.ok().body(Map.of("url", image.getUrl()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Internal Server Error."));
        }
    }
}
