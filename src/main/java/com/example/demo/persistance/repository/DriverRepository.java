package com.example.demo.persistance.repository;

import com.example.demo.persistance.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Transactional
    @Modifying
    @Query("update Driver driver SET driver.isActive = :isActive where driver.id = :id")
    void changeDriverStatus(@Param("isActive") Boolean isActive, @Param("id") Long id);
}
