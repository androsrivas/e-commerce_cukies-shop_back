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
    public ResponseEntity<ImageDTO> upload(@RequestParam(value = "file") MultipartFile file,
                                      @RequestParam(value = "productId", required = false) Integer productId) {

        return imageService.uploadImage(productId, file);
    }
}
