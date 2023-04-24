package com.example.test.exceptions;

public class canNotDeleteAddress extends RuntimeException {

    public canNotDeleteAddress() {
        super("Can not delete this address because is linked to client!");
    }
}
