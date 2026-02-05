package com.factoriaF5.cukies.exception.image;

import com.factoriaF5.cukies.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ImageUploadException extends ApiException {
    private final String imageName;

    public ImageUploadException(String message, String imageName) {
        super("Error al cargar imagen: " + imageName, HttpStatus.BAD_REQUEST);
        this.imageName = imageName;
    }


}
