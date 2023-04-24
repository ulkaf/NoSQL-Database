package com.plantStore.exceptions;


import com.plantStore.tables.Client;
import com.plantStore.tables.Product;

public class saleAlreadyExist extends nbdExceptions{

    public saleAlreadyExist(Client client, Product product) {
        super(String.format("%s %s sale of %s already exist!", client.getCName(), client.getCSurname(), product.getName()));
    }
}
