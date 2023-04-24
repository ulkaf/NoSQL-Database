package com.example.test.exceptions;

public class addressNotExistById extends RuntimeException {
    public addressNotExistById(Long id) {
        super(String.format("Address id : %d doesn't exist!", id));
    }
}
