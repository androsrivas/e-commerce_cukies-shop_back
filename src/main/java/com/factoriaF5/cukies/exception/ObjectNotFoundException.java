package com.factoriaF5.cukies.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String objectName, int id) {
        super("Could not find " + objectName + " with id: " + id);
    }
}
