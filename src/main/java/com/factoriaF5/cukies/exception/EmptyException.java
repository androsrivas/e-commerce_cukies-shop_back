package com.factoriaF5.cukies.exception;

public class EmptyException extends RuntimeException {
    public EmptyException() {
        super("no data");
    }
}
