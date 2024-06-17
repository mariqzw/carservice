package com.example.springdatabasicdemo.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "brands")
public class Brand extends TimeStampEntity {
    private String name;
    @OneToMany(mappedBy = "brand", cascade = CascadeType.MERGE)
    private Set<Model> models;

    public Brand(String name, Set<Model> models) {
        this.name = name;
        this.models = models;
    }

    protected Brand(){

    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "Brand { id=" + id + ", name=" + name + " }";
    }
}
