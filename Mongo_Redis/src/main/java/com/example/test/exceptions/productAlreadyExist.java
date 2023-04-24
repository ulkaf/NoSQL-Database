package com.example.test.exceptions;

public class productAlreadyExist extends nbdExceptions{
    public productAlreadyExist(String name) {
        super(String.format("Product with this name : %s already exists!", name));
    }
}
