package com.example.springdatabasicdemo.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class RoleNotFoundException extends EntityNotFoundException {
    public RoleNotFoundException() {
        super("Role not found");
    }
}
