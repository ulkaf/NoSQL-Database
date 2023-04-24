package com.example.test.exceptions;

public class thisAddressAlreadyExist extends RuntimeException {
    public thisAddressAlreadyExist(String city, String street, int street_number) {
        super(String.format("This address: %s %s %d already exist!", city,street,street_number));
    }
}
