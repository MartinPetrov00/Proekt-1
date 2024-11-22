package com.example.rentacar.repository;

import com.example.rentacar.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByCustomerName(String customerName);
}
