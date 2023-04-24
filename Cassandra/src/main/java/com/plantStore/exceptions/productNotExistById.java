package com.plantStore.exceptions;

import java.util.UUID;

public class productNotExistById extends nbdExceptions{
    public productNotExistById(UUID id) {
        super(String.format("Product id : %s doesn't exist!", id));
    }
}
