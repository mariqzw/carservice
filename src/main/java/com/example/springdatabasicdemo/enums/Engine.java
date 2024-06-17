package com.example.springdatabasicdemo.enums;

public enum Engine {
    Gasoline ("Gasoline", 0),
    Diesel ("Diesel", 1),
    Electric ("Electric", 2);
    private String type;
    private int number;

    Engine(String type, int number) {
        this.type = type;
        this.number = number;
    }
}
