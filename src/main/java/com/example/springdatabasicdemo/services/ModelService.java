package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.services.dtos.AddModelDto;
import com.example.springdatabasicdemo.services.dtos.ModelDto;

import java.util.List;

public interface ModelService {

    void register(AddModelDto addModelDto);

    AddModelDto findModelByName(String modelName);

    void updateModel(String originalModelName, AddModelDto modelDto);

    List<ModelDto> getAll();

}

