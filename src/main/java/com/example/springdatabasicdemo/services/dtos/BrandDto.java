package com.example.springdatabasicdemo.services.dtos;
import com.example.springdatabasicdemo.util.UniqueBrand;

import java.time.LocalDateTime;

public class BrandDto {
    @UniqueBrand
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;

    public BrandDto(String name) {
        this.name = name;
    }

    public BrandDto(){
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Brand { id=" + ", name=" + name + " }";
    }
}
