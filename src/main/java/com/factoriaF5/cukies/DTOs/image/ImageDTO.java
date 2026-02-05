package com.factoriaF5.cukies.DTOs.image;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    private String name;

    @NotNull(message = "El fichero no puede estar vacío.")
    private MultipartFile file;

    @NotNull(message = "El producto debe tener un ID.")
    private Integer productId;
}
