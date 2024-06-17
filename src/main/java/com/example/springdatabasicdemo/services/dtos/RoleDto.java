package com.example.springdatabasicdemo.services.dtos;

import java.util.UUID;

public class RoleDto {
    private UUID id;
    private UserRole role;

    public RoleDto(UUID id, UserRole role) {
        this.id = id;
        this.role = role;
    }

    public RoleDto() {
    }


    public enum UserRole {
        USER(0),
        ADMIN(1);
        int num;
        UserRole(int num){
            this.num = num;
        }
        public int getNum(){
            return num;
        }

        public void setNum(int num){
            this.num = num;
        }
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "Role { id=" + id + ", role=" + role + " }";
    }
}
