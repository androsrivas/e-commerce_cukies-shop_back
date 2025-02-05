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
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class ImageService {
    private static final String CLOUDINARY_FOLDER = "cukies_shop";
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
    public ResponseEntity<ImageDTO> uploadImage(Integer productId, MultipartFile file) {
        try {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("ID", productId));

            String imageName = "image-" + cleanProductName(file.getName());
            String imageUrl = cloudinaryService.uploadImage(file, CLOUDINARY_FOLDER);

            if (imageUrl == null) {
                throw new ImageUploadException(file.getOriginalFilename());
            }

            Image image = new Image(imageName, imageUrl);
            imageRepository.save(image);

            return ResponseEntity.ok(imageMapper.toDTO(image));
        } catch (ImageUploadException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    private String cleanProductName(String productName) {
        if (productName == null) return "";
        return productName.replace("[^a-zA-Z0-9_]", "_");
    }
}
