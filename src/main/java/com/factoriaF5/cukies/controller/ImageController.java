package com.factoriaF5.cukies.controller;

import com.factoriaF5.cukies.DTOs.image.ImageDTO;
import com.factoriaF5.cukies.repository.ImageRepository;
import com.factoriaF5.cukies.service.cloudinary.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class ImageController {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<Map> upload(@RequestParam("name") String name,
                                      @RequestParam("file") MultipartFile file,
                                      @RequestParam("productId") Integer productId) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Fichero vacío."));
        }

        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setName(name);
        imageDTO.setFile(file);
        imageDTO.setProductId(productId);

        return imageService.uploadImage(imageDTO);
    }
}
