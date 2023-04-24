package com.plantStore.exceptions;


import com.plantStore.documents.Client;
import com.plantStore.documents.Product;

public class saleNotExistbyEmailAndPName extends nbdExceptions{
    public saleNotExistbyEmailAndPName(Client client, Product product) {
        super(String.format("%s %s sale of %s doen't exist!", client.getCName(), client.getCSurname(), product.getName()));
    }
}
