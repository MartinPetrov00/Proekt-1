package com.example.rentacar.model;

import jakarta.persistence.*;

@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String location;
    private double pricePerDay;
    private String customerName; 
    private int daysRented;      
    private boolean hasAccidents;
    private String status;       

    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    public boolean isHasAccidents() {
        return hasAccidents;
    }

    public void setHasAccidents(boolean hasAccidents) {
        this.hasAccidents = hasAccidents;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
}
