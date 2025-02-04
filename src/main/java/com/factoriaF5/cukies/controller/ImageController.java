package com.factoriaF5.cukies.controller;

import com.factoriaF5.cukies.DTOs.image.ImageDTO;
import com.factoriaF5.cukies.repository.ImageRepository;
import com.factoriaF5.cukies.service.cloudinary.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ImageController {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<Map> upload(@ModelAttribute ImageDTO imageModel) {
        try {
            if (imageModel.getFile().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Fichero vacío"));
            }
            return imageService.uploadImage(imageModel);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("error", "Error al cargar imágen"));
        }
    }
}
