package com.example.demo.service;

import com.example.demo.dto.DriverDTO;
import com.example.demo.persistance.entity.Driver;

public interface DriverService {

    DriverDTO returnSuccessfulSavedNewDriverInstance(Driver driver);
    void changeDriverStatus(Long id, Boolean driverStatus);
}
