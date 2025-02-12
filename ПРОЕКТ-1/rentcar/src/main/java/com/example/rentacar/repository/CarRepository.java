package com.example.rentacar.repository;

import com.example.rentacar.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByLocation(String location);
}

