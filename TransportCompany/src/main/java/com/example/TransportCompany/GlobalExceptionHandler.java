package com.example.TransportCompany;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler
{

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    static class ErrorResponse
    {
        private LocalDateTime timestamp;
        private int status;
        private String error;

        public ErrorResponse(LocalDateTime timestamp, int status, String error)
        {
            this.timestamp = timestamp;
            this.status = status;
            this.error = error;
        }

        public LocalDateTime getTimestamp()
        {
            return timestamp;
        }

        public int getStatus()
        {
            return status;
        }

        public String getError()
        {
            return error;
        }
    }
}