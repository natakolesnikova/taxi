package com.example.demo.service.impl;

import com.example.demo.dto.DriverDTO;
import com.example.demo.exception.DriverNotFoundException;
import com.example.demo.exception.DriverPhoneNumberException;
import com.example.demo.persistance.entity.Driver;
import com.example.demo.persistance.repository.DriverRepository;
import com.example.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;


    @Override
    public DriverDTO returnSuccessfulSavedNewDriverInstance(Driver driver) {
        try {
            Driver driverSaved = driverRepository.saveAndFlush(driver);
            return convertModelToDTO(driverSaved);
        } catch (Exception e) {
            throw new DriverPhoneNumberException("Phone number is already exist");
        }
    }

    @Override
    public void changeDriverStatus(Long id, Boolean driverStatus) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new DriverNotFoundException("Driver with id " + id + " does not exist"));
        driverRepository.changeDriverStatus(driverStatus, driver.getId());
    }

    private DriverDTO convertModelToDTO(Driver driverSaved) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setCarColor(driverSaved.getCarColor());
        driverDTO.setCarModel(driverSaved.getCarModel());
        driverDTO.setCarNumber(driverSaved.getCarNumber());
        driverDTO.setCarYear(driverSaved.getCarYear());
        driverDTO.setCreationDate(driverSaved.getCreationDate());
        driverDTO.setDriverName(driverSaved.getDriverName());
        driverDTO.setId(driverSaved.getId());
        driverDTO.setIsActive(driverSaved.getIsActive());
        driverDTO.setPhone(driverSaved.getPhone());
        return driverDTO;
    }
}
