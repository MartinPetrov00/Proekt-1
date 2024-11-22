package com.example.rentacar.service;

import com.example.rentacar.model.Car;
import com.example.rentacar.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getCarsByLocation(String location) {
        return carRepository.findByLocation(location);
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        carRepository.delete(car);
    }
}
