package com.plantStore.exceptions;

public class clientNotExistbyEmail extends nbdExceptions{
    public clientNotExistbyEmail(String email) {
        super(String.format("Client with this email : %s doesn't exists", email));
    }
}
