package com.example.demo.controller;

import com.example.demo.dto.DriverDTO;
import com.example.demo.persistance.entity.Driver;
import com.example.demo.service.DriverService;
import com.example.demo.service.ValidationErrorService;
import com.example.demo.util.DriverUtil;
import io.swagger.annotations.*;
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
@Api(value = "/api/v1/driver", description = "Api for create and update driver instance")
public class DriverController {

    private static final String STATUS = "status";
    private static final String ID = "/{id}";

    @Value("${opti.driver.status.message}")
    private String driverStatusMessage;

    @Autowired
    private ValidationErrorService validationErrorService;
    @Autowired
    private DriverService driverService;

    @ApiOperation(httpMethod = "POST", value = "create new driver", response = DriverDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created driver"),
            @ApiResponse(code = 400, message = "Not valid input data")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<?> createNewDriver(@Valid @RequestBody @ApiParam(value = "Driver to save", required = true) Driver driver, BindingResult result) {
        ResponseEntity<?> responseEntity = validationErrorService.validateAndReturnErrors(result);

        if (nonNull(responseEntity)) {
            return responseEntity;
        }

        DriverDTO driverDTO = driverService.returnSuccessfulSavedNewDriverInstance(driver);
        return new ResponseEntity<>(driverDTO, HttpStatus.CREATED);
    }

    @ApiOperation(httpMethod = "PUT", value = "Update driver status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "driver status updated successfully"),
            @ApiResponse(code = 400, message = "Invalid params fot updating driver")
    })
    @PutMapping(path = ID)
    @Transactional
    public ResponseEntity<?> changeDriverStatus(@ApiParam(value = "id of driver", required = true) @PathVariable Long id,
                                                @ApiParam(value = "value to make driver active=true or not active=false", required = true) @RequestParam(STATUS) Boolean driverStatus) {
        driverService.changeDriverStatus(id, driverStatus);
        return ResponseEntity.ok(DriverUtil.getResponseMap(driverStatusMessage));
    }
}
