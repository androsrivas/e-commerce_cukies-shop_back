package com.factoriaF5.cukies.exception.image;

import com.factoriaF5.cukies.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ImageUploadException extends ApiException {
    private final String imageName;

    public ImageUploadException(String imageName) {
        super("Could not upload image " + imageName + " to the server" , HttpStatus.BAD_REQUEST);
        this.imageName = imageName;
    }
}
