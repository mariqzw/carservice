package com.example.springdatabasicdemo.models;

import com.example.springdatabasicdemo.enums.Category;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "models")
public class Model extends TimeStampEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @OneToMany(mappedBy = "model", cascade = CascadeType.MERGE)
    private Set<Offer> offers;
    private int start_year;
    private int end_year;
    @ManyToOne(optional = false)
    private Brand brand;

    public Model(String name, Category category, int start_year, int end_year) {
        this.name = name;
        this.category = category;
        this.start_year = start_year;
        this.end_year = end_year;
    }

    protected Model(){
    }


    @Column(name = "category", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "end_year", nullable = false)
    public int getEnd_year() {
        return end_year;
    }

    public void setEnd_year(int end_year) {
        this.end_year = end_year;
    }

    @Column(name = "start_year", nullable = false)
    public int getStart_year() {
        return start_year;
    }

    public void setStart_year(int start_year) {
        this.start_year = start_year;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable=false)
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

}
