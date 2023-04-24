package com.example.kafka_nbd.exceptions;

public class addressNotExistbyAll extends nbdExceptions{
    public addressNotExistbyAll(String city, String street, int street_number) {
        super(String.format("Adrress: %s %s %d doesn't exist!",city,street,street_number));
    }
}
