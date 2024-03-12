package com.example.TransportCompany.exceptions;

public class BudgetTooLowException extends RuntimeException {
    public BudgetTooLowException(String message) {
        super(message);
    }
}