package com.example.rentacar.controller;

import com.example.rentacar.model.Car;
import com.example.rentacar.model.Offer;
import com.example.rentacar.repository.CarRepository;
import com.example.rentacar.repository.OfferRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    private final OfferRepository offerRepository;
    private final CarRepository carRepository;

    public OfferController(OfferRepository offerRepository, CarRepository carRepository) {
        this.offerRepository = offerRepository;
        this.carRepository = carRepository;
    }

    @PostMapping
    public ResponseEntity<?> createOffer(@RequestParam Long carId, @RequestBody Offer offer) {
        Optional<Car> carOptional = carRepository.findById(carId);
        if (carOptional.isEmpty() || !carOptional.get().isActive()) {
            return ResponseEntity.badRequest().body("Invalid car ID or car is inactive.");
        }

        Car car = carOptional.get();
        double totalPrice = calculatePrice(car.getPricePerDay(), offer.getDaysRented(), offer.isHasAccidents());
        offer.setCar(car);
        offer.setStatus("CREATED");

        offerRepository.save(offer); 
        return ResponseEntity.ok("Offer created with total price: " + totalPrice);
    }

    @GetMapping
    public List<Offer> getOffersForCustomer(@RequestParam String customerName) {
        return offerRepository.findByCustomerName(customerName);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long id) { 
        return offerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOffer(@PathVariable Long id) {
        Optional<Offer> offerOptional = offerRepository.findById(id); 
        if (offerOptional.isPresent()) {
            Offer offer = offerOptional.get();
            offer.setStatus("DELETED");
            offerRepository.save(offer);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<?> acceptOffer(@PathVariable Long id) {
        Optional<Offer> offerOptional = offerRepository.findById(id); 
        if (offerOptional.isPresent()) {
            Offer offer = offerOptional.get();
            offer.setStatus("ACCEPTED");
            offerRepository.save(offer);
            return ResponseEntity.ok("Offer accepted.");
        }
        return ResponseEntity.notFound().build();
    }

    private double calculatePrice(double pricePerDay, int days, boolean hasAccidents) {
        double totalPrice = pricePerDay * days;

        LocalDate currentDate = LocalDate.now();
        for (int i = 0; i < days; i++) {
            LocalDate date = currentDate.plusDays(i);
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                totalPrice += pricePerDay * 0.10;
            }
        }

        if (hasAccidents) {
            totalPrice += 200;
        }

        return totalPrice;
    }
}
