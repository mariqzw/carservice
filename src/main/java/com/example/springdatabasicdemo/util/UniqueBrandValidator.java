package com.example.springdatabasicdemo.util;

import com.example.springdatabasicdemo.repositories.BrandRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class UniqueBrandValidator implements ConstraintValidator<UniqueBrand, String> {
    private final BrandRepository brandRepository;

    public UniqueBrandValidator(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return brandRepository.findByName(value).isEmpty();
    }
}
