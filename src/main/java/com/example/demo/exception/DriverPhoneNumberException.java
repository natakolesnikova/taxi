package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DriverPhoneNumberException extends RuntimeException {

    public DriverPhoneNumberException(String message) {
        super(message);
    }
}
