package com.factoriaF5.cukies.DTOs.image;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    private String name;
    private MultipartFile file;
}
