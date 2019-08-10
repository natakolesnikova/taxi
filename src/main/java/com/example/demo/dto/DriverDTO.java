package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {
    private Long id;
    private BigInteger phone;
    private String driverName;
    private String carModel;
    private String carNumber;
    private String carColor;
    private Integer carYear;
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private Date creationDate;
    private Boolean isActive;
}
