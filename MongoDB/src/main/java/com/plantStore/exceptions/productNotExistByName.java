package com.plantStore.exceptions;

public class productNotExistByName extends nbdExceptions{
    public productNotExistByName(String name) {
        super(String.format("Product with this name : %s doesn't exists", name));
    }
}
