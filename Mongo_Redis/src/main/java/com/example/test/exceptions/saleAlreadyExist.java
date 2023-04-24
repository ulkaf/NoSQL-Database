package com.example.test.exceptions;


import com.example.test.documents.Client;
import com.example.test.documents.Product;

public class saleAlreadyExist extends nbdExceptions{

    public saleAlreadyExist(Client client, Product product) {
        super(String.format("%s %s sale of %s already exist!", client.getCName(), client.getCSurname(), product.getName()));
    }
}
