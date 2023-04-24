package com.plantStore.exceptions;


import com.plantStore.tables.Client;
import com.plantStore.tables.Product;

public class saleNotExistbyEmailAndPName extends nbdExceptions{
    public saleNotExistbyEmailAndPName(Client client, Product product) {
        super(String.format("%s %s sale of %s doen't exist!", client.getCName(), client.getCSurname(), product.getName()));
    }
}
