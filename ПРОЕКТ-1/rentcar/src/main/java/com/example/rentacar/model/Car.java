package com.example.rentacar.model;

import jakarta.persistence.*;

@Entity
public class Car {
    public static final String ALLOWED_LOCATIONS = null;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String location;
    private double pricePerDay;

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getLocation() {
        return location;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    // Сетъри
    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public boolean isActive() {
        throw new UnsupportedOperationException("Unimplemented method 'isActive'");
    }

    public void setStatus(String string) {
        throw new UnsupportedOperationException("Unimplemented method 'setStatus'");
    }

    public void setActive(boolean active) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setActive'");
    }
}