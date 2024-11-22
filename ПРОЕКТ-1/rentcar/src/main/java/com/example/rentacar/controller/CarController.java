package com.example.rentacar.controller;

import com.example.rentacar.model.Car;
import com.example.rentacar.repository.CarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // 1. Листване на всички автомобили на база локация
    @GetMapping
    public ResponseEntity<List<Car>> getCarsByLocation(@RequestParam String location) {
        if (!Car.ALLOWED_LOCATIONS.contains(location)) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(carRepository.findByLocation(location));
    }

    // 2. Листване на конкретен автомобил по ID
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> car = carRepository.findById(id);
        return car.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // 3. Актуализация на съществуващ автомобил
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        if (!Car.ALLOWED_LOCATIONS.contains(updatedCar.getLocation())) {
            return ResponseEntity.badRequest().body("Invalid location. Allowed locations are: " + Car.ALLOWED_LOCATIONS);
        }
        return carRepository.findById(id)
                .map(existingCar -> {
                    existingCar.setBrand(updatedCar.getBrand());
                    existingCar.setModel(updatedCar.getModel());
                    existingCar.setLocation(updatedCar.getLocation());
                    existingCar.setPricePerDay(updatedCar.getPricePerDay());
                    existingCar.setActive(updatedCar.isActive());
                    carRepository.save(existingCar);
                    return ResponseEntity.ok("Car updated successfully.");
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. Изтриване на автомобил от системата
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            carRepository.delete(car.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // 5. Добавяне на нов автомобил
    @PostMapping
    public ResponseEntity<?> addCar(@RequestBody Car newCar) {
        if (!Car.ALLOWED_LOCATIONS.contains(newCar.getLocation())) {
            return ResponseEntity.badRequest().body("Invalid location. Allowed locations are: " + Car.ALLOWED_LOCATIONS);
        }
        carRepository.save(newCar);
        return ResponseEntity.ok("Car added successfully.");
    }
}
