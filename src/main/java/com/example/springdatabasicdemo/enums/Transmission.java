package com.example.springdatabasicdemo.enums;

public enum Transmission {
    Manual ("Manual", 0),
    Automatic ("Automatic", 1);
    private String type;
    private int number;

    Transmission(String type, int number) {
        this.type = type;
        this.number = number;
    }
}
