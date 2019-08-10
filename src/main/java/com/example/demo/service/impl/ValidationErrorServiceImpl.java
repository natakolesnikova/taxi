package com.example.demo.service.impl;

import com.example.demo.persistance.entity.Driver;
import com.example.demo.service.ValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationErrorServiceImpl implements ValidationErrorService {

    @Override
    public ResponseEntity<?> validateAndReturnErrors(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error: result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<Map>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

}
