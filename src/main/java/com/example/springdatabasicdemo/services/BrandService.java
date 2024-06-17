package com.example.springdatabasicdemo.services;
import com.example.springdatabasicdemo.services.dtos.BrandDto;
import com.example.springdatabasicdemo.services.dtos.ShowBrandDto;

import java.util.List;

public interface BrandService {

    void addBrand(BrandDto brand);

    List<ShowBrandDto> findAll();

    void updateBrand(String originalBrandName, BrandDto brandDto);

    BrandDto findBrandByName(String brandName);
}
