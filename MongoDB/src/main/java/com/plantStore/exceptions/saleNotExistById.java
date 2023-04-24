package com.plantStore.exceptions;

public class saleNotExistById extends nbdExceptions{

    public saleNotExistById(String id) {
        super(String.format("Sale with id: %d doesn't exist!", id));
    }
}
