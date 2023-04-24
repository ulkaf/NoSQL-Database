package com.example.kafka_nbd.exceptions;


import com.example.kafka_nbd.documents.Client;
import com.example.kafka_nbd.documents.Product;

public class saleNotExistbyEmailAndPName extends nbdExceptions{
    public saleNotExistbyEmailAndPName(Client client, Product product) {
        super(String.format("%s %s sale of %s doen't exist!", client.getCName(), client.getCSurname(), product.getName()));
    }
}
