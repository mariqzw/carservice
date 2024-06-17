package com.example.springdatabasicdemo.enums;

public enum RoleEnum {
    User ("User", 0),
    Admin ("Admin", 1);

    private String type;
    private int number;

    RoleEnum(String type, int number) {
        this.type = type;
        this.number = number;
    }
}

