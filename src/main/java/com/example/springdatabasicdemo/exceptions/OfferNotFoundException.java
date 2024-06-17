package com.example.springdatabasicdemo.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class OfferNotFoundException extends EntityNotFoundException {
    public OfferNotFoundException() {
        super("Offer not found");
    }
}
