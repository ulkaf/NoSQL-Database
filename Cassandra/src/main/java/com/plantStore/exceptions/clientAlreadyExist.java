package com.plantStore.exceptions;

public class clientAlreadyExist extends nbdExceptions{
    public clientAlreadyExist(String email) {
        super(String.format("Client with this email : %s already exists!", email));
    }
}
