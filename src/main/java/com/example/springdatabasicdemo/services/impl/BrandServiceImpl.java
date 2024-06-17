package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.services.dtos.BrandDto;
import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.repositories.BrandRepository;
import com.example.springdatabasicdemo.services.BrandService;
import com.example.springdatabasicdemo.exceptions.BrandNotFoundException;
import com.example.springdatabasicdemo.services.dtos.ShowBrandDto;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@EnableCaching
public class BrandServiceImpl implements BrandService {
    private BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public BrandServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @CacheEvict(cacheNames = "brand", allEntries = true)
    @Override
    public void addBrand(BrandDto brandDto) {
        if (!this.validationUtil.isValid(brandDto)) {

            this.validationUtil
                    .violations(brandDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            return;
        }
        brandDto.setCreated(LocalDateTime.now());
        brandDto.setModified(LocalDateTime.now());
        brandRepository.saveAndFlush(modelMapper.map(brandDto, Brand.class));
    }

    @Cacheable("brand")
    @Override
    public List<ShowBrandDto> findAll() {
        return brandRepository.findAll()
                .stream()
                .map(brand -> modelMapper.map(brand, ShowBrandDto.class))
                .collect(Collectors.toList());
    }


    @Cacheable("brand")
    public BrandDto findBrandByName(String brandName) {
        return brandRepository.findByName(brandName)
                .map(brand -> modelMapper.map(brand, BrandDto.class))
                .orElse(null);
    }

    @CacheEvict(cacheNames = "brand", allEntries = true)
    public void updateBrand(String originalBrandName, BrandDto brandDto) {
        Optional<Brand> existingBrandOptional = brandRepository.findByName(originalBrandName);

        if (existingBrandOptional.isPresent()) {
            Brand existingBrand = existingBrandOptional.get();

            existingBrand.setName(brandDto.getName());
            existingBrand.setModified(LocalDateTime.now());

            brandRepository.saveAndFlush(existingBrand);
        } else {
            throw new BrandNotFoundException();
        }
    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

}