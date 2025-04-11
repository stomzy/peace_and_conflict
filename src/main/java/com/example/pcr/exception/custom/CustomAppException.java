package com.example.pcr.exception.custom;

public class CustomAppException extends RuntimeException {
    private final int status;

    public CustomAppException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
