package com.example.springdatabasicdemo.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class BrandNotFoundException extends EntityNotFoundException {
    public BrandNotFoundException() {
        super("Brand not found");
    }
}
