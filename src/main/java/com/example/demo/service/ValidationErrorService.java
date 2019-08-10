package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface ValidationErrorService {

    ResponseEntity<?> validateAndReturnErrors(BindingResult result);
}
