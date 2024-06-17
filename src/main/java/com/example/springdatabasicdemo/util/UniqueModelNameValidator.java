package com.example.springdatabasicdemo.util;

import com.example.springdatabasicdemo.repositories.ModelRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueModelNameValidator implements ConstraintValidator<UniqueModelName, String> {
    private ModelRepository modelRepository;

    @Autowired
    public UniqueModelNameValidator(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return modelRepository.findByName(value).isEmpty();
    }
}
