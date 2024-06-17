package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.services.dtos.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void delete(UUID id);

    List<UserDto> getAll();

}

