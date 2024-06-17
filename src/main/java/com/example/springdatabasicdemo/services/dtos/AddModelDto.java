package com.example.springdatabasicdemo.services.dtos;

import com.example.springdatabasicdemo.enums.Category;
import com.example.springdatabasicdemo.util.UniqueModelName;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class AddModelDto {
    @UniqueModelName
    private String name;
    private String brand;
    private Category category;
    private int start_year;
    private int end_year;
    private LocalDateTime created;
    private LocalDateTime modified;


    @NotEmpty(message = "Model name must not be null or empty!")
    @Size(min = 2, message = "Model name should be at least 2 characters long!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @NotNull(message = "Start year must not be null or empty!")
    @Min(value = 1885, message = "Start year must be at least 1885")
    public int getEnd_year() {
        return end_year;
    }

    public void setEnd_year(int end_year) {
        this.end_year = end_year;
    }

    @Min(value = 1885, message = "Start year must be at least 1885")
    public int getStart_year() {
        return start_year;
    }

    public void setStart_year(int start_year) {
        this.start_year = start_year;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}

