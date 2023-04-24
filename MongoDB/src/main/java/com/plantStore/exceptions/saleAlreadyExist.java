package com.plantStore.exceptions;


import com.plantStore.documents.Client;
import com.plantStore.documents.Product;

public class saleAlreadyExist extends nbdExceptions{

    public saleAlreadyExist(Client client, Product product) {
        super(String.format("%s %s sale of %s already exist!", client.getCName(), client.getCSurname(), product.getName()));
    }
}
