package com.example.springdatabasicdemo.services.dtos;

import com.example.springdatabasicdemo.enums.Category;

public class ModelDto {
    private String name;
    private Category category;
    private int start_year;
    private int end_year;
    private String brandName;
    private String brand;


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getEnd_year() {
        return end_year;
    }

    public void setEnd_year(int end_year) {
        this.end_year = end_year;
    }

    public int getStart_year() {
        return start_year;
    }

    public void setStart_year(int start_year) {
        this.start_year = start_year;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    @Override
    public String toString() {
        return "Model { id=" + ", category=" + category + ", end_year=" + end_year + ", name=" + name + ", start_year=" + start_year + " }";
    }
}