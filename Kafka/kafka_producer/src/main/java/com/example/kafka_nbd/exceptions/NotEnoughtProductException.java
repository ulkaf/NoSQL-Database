package com.example.kafka_nbd.exceptions;

public class NotEnoughtProductException extends nbdExceptions{
    public NotEnoughtProductException(String name) {
        super(String.format("Not enough product  %s",name));
    }
}
