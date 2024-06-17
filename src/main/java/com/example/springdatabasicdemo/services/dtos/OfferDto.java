package com.example.springdatabasicdemo.services.dtos;

import com.example.springdatabasicdemo.enums.Transmission;
import jakarta.validation.constraints.*;
import com.example.springdatabasicdemo.enums.Engine;

import java.math.BigDecimal;
import java.util.UUID;

public class OfferDto {
    private UUID id;
    private String description;
    private Engine engine;
    private String imageUrl;
    private int mileage;
    private BigDecimal price;
    private Transmission transmission;
    private int year;
    private UserDto user;
    private ModelDto model;


    public OfferDto(String description, Engine engine, String imageUrl, int mileage, BigDecimal price, Transmission transmission, int year, UserDto user, ModelDto model) {
        this.description = description;
        this.engine = engine;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.price = price;
        this.transmission = transmission;
        this.year = year;
        this.user = user;
        this.model = model;
    }

    public OfferDto(){
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public ModelDto getModel() {
        return model;
    }

    public void setModel(ModelDto model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Offer { id=" + ", transmission=" + transmission + ", description=" + description + ", engine=" + engine + ", imageUrl=" + imageUrl + ", mileage=" + mileage + ", price=" + price + ", year=" + year + " }";
    }
}
