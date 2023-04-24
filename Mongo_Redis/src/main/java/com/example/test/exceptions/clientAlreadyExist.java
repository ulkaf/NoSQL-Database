package com.example.test.exceptions;

public class clientAlreadyExist extends RuntimeException {
    public clientAlreadyExist(String email) {
        super(String.format("Client with this email : %s already exists!", email));
    }
}
