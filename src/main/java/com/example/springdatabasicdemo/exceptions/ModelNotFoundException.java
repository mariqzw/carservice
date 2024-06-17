package com.example.springdatabasicdemo.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ModelNotFoundException extends EntityNotFoundException {
    public ModelNotFoundException() {
        super("Model not found");
    }
}
