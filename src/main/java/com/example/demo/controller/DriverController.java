package com.example.demo.controller;

import com.example.demo.dto.DriverDTO;
import com.example.demo.persistance.entity.Driver;
import com.example.demo.service.DriverService;
import com.example.demo.service.ValidationErrorService;
import com.example.demo.util.DriverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/api/v1/driver")
public class DriverController {

    private static final String STATUS = "status";
    private static final String ID = "/{id}";

    @Value("${opti.driver.status.message}")
    private String driverStatusMessage;

    @Autowired
    private ValidationErrorService validationErrorService;
    @Autowired
    private DriverService driverService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createNewDriver(@Valid @RequestBody Driver driver, BindingResult result) {
        ResponseEntity<?> responseEntity = validationErrorService.validateAndReturnErrors(result);

        if (nonNull(responseEntity)) {
            return responseEntity;
        }

        DriverDTO driverDTO = driverService.returnSuccessfulSavedNewDriverInstance(driver);
        return new ResponseEntity<>(driverDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = ID)
    @Transactional
    public ResponseEntity<?> makeDriverActive(@PathVariable Long id, @RequestParam(STATUS) Boolean driverStatus) {
        driverService.changeDriverStatus(id, driverStatus);
        return ResponseEntity.ok(DriverUtil.getResponseMap(driverStatusMessage));
    }
}
