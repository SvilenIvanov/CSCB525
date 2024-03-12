package com.example.TransportCompany.exceptions;

public class NotEmployedException extends RuntimeException {
    public NotEmployedException(String message) {
        super(message);
    }
}