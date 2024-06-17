package com.example.springdatabasicdemo.models;

import com.example.springdatabasicdemo.enums.Engine;
import com.example.springdatabasicdemo.enums.Transmission;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class Offer extends TimeStampEntity {
    @ManyToOne(optional = false)
    private Model model;
    @ManyToOne(optional = false)
    private User user;
    private String description;
    @Enumerated(EnumType.STRING)
    private Engine engine;
    private int mileage;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    private int year;
    private BigDecimal price;
    private String imageUrl;



    public Offer(String description, Engine engine, int mileage, Transmission transmission, int year, BigDecimal price, String imageUrl) {
        this.description = description;
        this.engine = engine;
        this.mileage = mileage;
        this.transmission = transmission;
        this.year = year;
        this.price = price;
        this.imageUrl = imageUrl;
    }


    protected Offer(){
    }


    @JoinColumn(name = "model_id", referencedColumnName = "id", nullable=false)
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable=false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "engine", nullable = false)
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }


    @Column(name = "mileage", nullable = false)
    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Column(name = "transmission", nullable = false)
    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Column(name = "year", nullable = false)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "imageUrl", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Override
    public String toString() {
        return "Offer{" +
                "model=" + model +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", engine=" + engine +
                ", mileage=" + mileage +
                ", transmission=" + transmission +
                ", year=" + year +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
