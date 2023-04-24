package com.plantStore.exceptions;

import java.util.UUID;

public class saleNotExistById extends nbdExceptions{

    public saleNotExistById(UUID id) {
        super(String.format("Sale with id: %d doesn't exist!", id));
    }
}
