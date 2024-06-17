package com.example.springdatabasicdemo.repositories;
import com.example.springdatabasicdemo.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {
    List<Offer> findTop3ByOrderByPriceDesc();
    List<Offer> findAllByUserUsername(String username);
    List<Offer> findAllByYear(int year);
    List<Offer> findAllByMileage(int mileage);
}

