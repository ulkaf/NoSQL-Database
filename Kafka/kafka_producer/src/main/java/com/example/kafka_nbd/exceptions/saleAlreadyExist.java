package com.example.kafka_nbd.exceptions;


import com.example.kafka_nbd.documents.Client;
import com.example.kafka_nbd.documents.Product;

public class saleAlreadyExist extends nbdExceptions{

    public saleAlreadyExist(Client client, Product product) {
        super(String.format("%s %s sale of %s already exist!", client.getCName(), client.getCSurname(), product.getName()));
    }
}
