package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.repositories.UserRepository;
import com.example.springdatabasicdemo.services.dtos.AddOfferDto;
import com.example.springdatabasicdemo.services.dtos.OfferDto;
import com.example.springdatabasicdemo.models.Offer;
import com.example.springdatabasicdemo.repositories.ModelRepository;
import com.example.springdatabasicdemo.repositories.OfferRepository;
import com.example.springdatabasicdemo.services.OfferService;

import com.example.springdatabasicdemo.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
//@EnableCaching
public class OfferServiceImpl implements OfferService {

    private OfferRepository offerRepository;
    private ModelRepository modelRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @CacheEvict(cacheNames = "offer", allEntries = true)
    public void register(AddOfferDto offerDto, String username) {
        if (!this.validationUtil.isValid(offerDto)) {

            this.validationUtil
                    .violations(offerDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            return;
        }
        offerDto.setCreated(LocalDateTime.now());
        offerDto.setModified(LocalDateTime.now());
        Offer offer = modelMapper.map(offerDto, Offer.class);
        offer.setModel(modelRepository.findByName(offerDto.getModel()).orElse(null));
        offer.setUser(userRepository.findByUsername(username).orElse(null));
        offerRepository.saveAndFlush(offer);
    }

    @Cacheable("offer")
    @Override
    public List<OfferDto> findOffersByUsername(String username) {
        List<Offer> offers = offerRepository.findAllByUserUsername(username);
        return offers.stream()
                .map(o -> modelMapper.map(o, OfferDto.class))
                .collect(Collectors.toList());
    }

    @Cacheable("offer")
    @Override
    public List<OfferDto> getTop3ExpensiveOffers() {
        List<Offer> top3ExpensiveOffers = offerRepository.findTop3ByOrderByPriceDesc();
        return top3ExpensiveOffers.stream()
                .map(o -> modelMapper.map(o, OfferDto.class))
                .collect(Collectors.toList());
    }


    @Cacheable("offer")
    @Override
    public List<OfferDto> getAll() {
        return offerRepository.findAll()
                .stream()
                .map((o) -> modelMapper.map(o, OfferDto.class))
                .collect(Collectors.toList());
    }

    @CacheEvict(cacheNames = "offer", allEntries = true)
    @Override
    public void delete(UUID id) {
        offerRepository.deleteById(id);
    }

    @Cacheable("offer")
    @Override
    public List<OfferDto> getOffersByYear(int year) {
        return offerRepository.findAllByYear(year)
                .stream()
                .map(o -> modelMapper.map(o, OfferDto.class))
                .collect(Collectors.toList());
    }

    @Cacheable("offer")
    @Override
    public List<OfferDto> getOffersByMileage(int mileage) {
        return offerRepository.findAllByMileage(mileage)
                .stream()
                .map(o -> modelMapper.map(o, OfferDto.class))
                .collect(Collectors.toList());
    }


    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}