package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DriverPhoneNumberException.class)
    public final ResponseEntity<Object> handleDriverPhoneNumberException(DriverPhoneNumberException exception) {
        DriverPhoneNumberExceptionResponse response = new DriverPhoneNumberExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DriverNotFoundException.class)
    public final ResponseEntity<Object> handleDriverNotFoundException(DriverNotFoundException exception) {
        DriverNotFoundExceptionResponse response = new DriverNotFoundExceptionResponse("", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
