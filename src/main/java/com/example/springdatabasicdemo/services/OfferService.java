package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.services.dtos.AddOfferDto;
import com.example.springdatabasicdemo.services.dtos.OfferDto;

import java.util.List;
import java.util.UUID;

public interface OfferService {

    void register(AddOfferDto offer, String username);

    void delete(UUID id);

    List<OfferDto> getAll();

    List<OfferDto> getTop3ExpensiveOffers();

    List<OfferDto> findOffersByUsername(String username);

    List<OfferDto> getOffersByYear(int year);

    List<OfferDto> getOffersByMileage(int mileage);
    

}