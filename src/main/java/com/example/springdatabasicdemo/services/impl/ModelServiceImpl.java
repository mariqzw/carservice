package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.exceptions.BrandNotFoundException;
import com.example.springdatabasicdemo.exceptions.ModelNotFoundException;
import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.models.Model;
import com.example.springdatabasicdemo.services.dtos.AddModelDto;
import com.example.springdatabasicdemo.services.dtos.ModelDto;
import com.example.springdatabasicdemo.repositories.ModelRepository;
import com.example.springdatabasicdemo.repositories.BrandRepository;
import com.example.springdatabasicdemo.services.ModelService;

import com.example.springdatabasicdemo.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
//@EnableCaching
public class ModelServiceImpl implements ModelService {

    private ModelRepository modelRepository;
    private BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    @Autowired
    public ModelServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @CacheEvict(cacheNames = "model", allEntries = true)
    @Override
    public void register(AddModelDto addModelDto) {
        if (!this.validationUtil.isValid(addModelDto)) {

            this.validationUtil
                    .violations(addModelDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            return;
        }
        addModelDto.setCreated(LocalDateTime.now());
        addModelDto.setModified(LocalDateTime.now());
        Model model = modelMapper.map(addModelDto, Model.class);
        model.setBrand(brandRepository.findByName(addModelDto.getBrand()).orElse(null));
        modelRepository.saveAndFlush(model);
    }


    @Cacheable("model")
    @Override
    public List<ModelDto> getAll() {
        return modelRepository.findAll()
                .stream()
                .map((s) -> modelMapper.map(s, ModelDto.class))
                .collect(Collectors.toList());
    }

    @Cacheable("model")
    public AddModelDto findModelByName(String modelName) {
        return modelRepository.findByName(modelName)
                .map(model -> modelMapper.map(model, AddModelDto.class))
                .orElse(null);
    }



    @CacheEvict(cacheNames = "model", allEntries = true)
    public void updateModel(String originalModelName, AddModelDto modelDto) {
        Optional<Model> existingModelOptional = modelRepository.findByName(originalModelName);

        if (existingModelOptional.isPresent()) {
            Model existingModel = existingModelOptional.get();

            Optional<Brand> brandOptional = brandRepository.findByName(modelDto.getBrand());
            if (brandOptional.isPresent()) {
                existingModel.setBrand(brandOptional.get());
            } else {
                throw new BrandNotFoundException();
            }

            existingModel.setName(modelDto.getName());
            existingModel.setCategory(modelDto.getCategory());
            existingModel.setStart_year(modelDto.getStart_year());
            existingModel.setEnd_year(modelDto.getEnd_year());
            existingModel.setModified(LocalDateTime.now());

            modelRepository.saveAndFlush(existingModel);
        } else {
            throw new ModelNotFoundException();
        }
    }

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

}