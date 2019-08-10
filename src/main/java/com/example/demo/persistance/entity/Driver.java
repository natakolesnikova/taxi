package com.example.demo.persistance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigInteger;
import java.util.Date;

import static java.lang.Boolean.FALSE;

@Entity
@Table(name = "drivers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Phone number is required")
    @Column(name = "phone", unique = true)
    @Positive(message = "Please enter valid phone number")
    @Min(message = "Please enter valid phone number", value = 1111111111)
    private BigInteger phone;

    @NotBlank(message = "Name is required")
    @Column(name = "name")
    private String driverName;

    @NotBlank(message = "Car model is required")
    @Column(name = "car_model")
    private String carModel;

    @NotBlank(message = "Car number is required")
    @Column(name = "car_number")
    private String carNumber;

    @NotBlank(message = "Car colour is required")
    @Column(name = "car_color")
    private String carColor;

    @NotNull(message = "Car year is required")
    @Positive
    @Column(name = "car_year")
    @Min(value = 1920, message = "Please enter valid year")
    @Max(value = 2200, message = "Please enter valid year")
    private Integer carYear;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(name = "creation_date", updatable = false)
    private Date creationDate;

    @Column(name = "is_active")
    private Boolean isActive;

    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
        this.isActive = FALSE;
    }
}
