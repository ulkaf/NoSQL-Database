package com.example.test.exceptions;

public class productNotExistById extends nbdExceptions{
    public productNotExistById(String id) {
        super(String.format("Product id : %s doesn't exist!", id));
    }
}
