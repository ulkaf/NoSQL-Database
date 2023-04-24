package com.plantStore.exceptions;

public class clientNotExistById extends nbdExceptions{

    public clientNotExistById(String id) {
        super(String.format("Client id : %s doesn't exist!", id));
    }
}
