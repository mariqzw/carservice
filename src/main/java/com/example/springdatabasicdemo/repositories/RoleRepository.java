package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.enums.RoleEnum;
import com.example.springdatabasicdemo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findRoleByName(RoleEnum role);
}

