package com.example.test.exceptions;

public class clientNotExistById extends RuntimeException {

    public clientNotExistById(String id) {
        super(String.format("Client id : %s doesn't exist!", id));
    }
}
