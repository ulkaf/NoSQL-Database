package com.plantStore.exceptions;

import java.util.UUID;

public class clientNotExistById extends nbdExceptions{

    public clientNotExistById(UUID id) {
        super(String.format("Client id : %s doesn't exist!", id));
    }
}
