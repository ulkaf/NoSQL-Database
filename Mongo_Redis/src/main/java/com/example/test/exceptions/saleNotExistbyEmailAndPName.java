package com.example.test.exceptions;


import com.example.test.documents.Client;
import com.example.test.documents.Product;

public class saleNotExistbyEmailAndPName extends nbdExceptions{
    public saleNotExistbyEmailAndPName(Client client, Product product) {
        super(String.format("%s %s sale of %s doen't exist!", client.getCName(), client.getCSurname(), product.getName()));
    }
}
