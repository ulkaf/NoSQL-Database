package com.example.test.exceptions;

public class clientNotExistbyEmail extends RuntimeException {
    public clientNotExistbyEmail(String email) {
        super(String.format("Client with this email : %s doesn't exists", email));
    }
}
