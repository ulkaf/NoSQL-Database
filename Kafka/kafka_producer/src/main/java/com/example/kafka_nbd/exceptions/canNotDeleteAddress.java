package com.example.kafka_nbd.exceptions;

public class canNotDeleteAddress extends nbdExceptions{

    public canNotDeleteAddress() {
        super("Can not delete this address because is linked to client!");
    }
}
