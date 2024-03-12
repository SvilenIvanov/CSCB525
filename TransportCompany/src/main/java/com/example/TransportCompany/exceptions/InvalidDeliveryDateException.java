package com.example.TransportCompany.exceptions;

public class InvalidDeliveryDateException extends RuntimeException {
    public InvalidDeliveryDateException(String message) {
        super(message);
    }
}