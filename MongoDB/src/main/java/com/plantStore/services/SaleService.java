package com.plantStore.services;

import com.plantStore.documents.Address;
import com.plantStore.documents.Client;
import com.plantStore.documents.Product;
import com.plantStore.documents.Sale;
import com.plantStore.exceptions.NotEnoughtProductException;
import com.plantStore.exceptions.saleNotExistById;
import com.plantStore.exceptions.saleNotExistbyEmailAndPName;
import com.plantStore.repositories.ClientRepository;
import com.plantStore.repositories.ProductRepository;
import com.plantStore.repositories.SalesRepository;

import java.time.LocalDateTime;

public class SaleService {

    private final SalesRepository salesRepository;

    private final ProductRepository productRepository;

    private final ClientRepository clientRepository;

    public SaleService(SalesRepository salesRepository, ProductRepository productRepository, ClientRepository clientRepository) {
        this.salesRepository = salesRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
    }

    public Sale findSaleByIdIfExist (String id) {
        return salesRepository.findById(id).orElseThrow(() -> new saleNotExistById(id));
    }

    public Client findSaleClient (String id) {
        return findSaleByIdIfExist(id).getClient();
    }

    public Product findSaleProduct (String id) {
        return findSaleByIdIfExist(id).getProduct();
    }

    public void deleteSale(String id) {
        salesRepository.delete(findSaleByIdIfExist(id));
    }

    public void deleteAll() {
        if (!salesRepository.findAll().isEmpty())
            salesRepository.deleteAll();
    }

    public Sale findSaleByClientAndAddressIfExist (Client client, Product product) {
        return salesRepository.findByProductNameAndClientEmail(product.getName(), client.getEmail()).orElseThrow(() -> new saleNotExistbyEmailAndPName(client, product));
    }

    public Sale addSale (Integer sProductCount, Client client, Product product, ProductService productService) {
        if (sProductCount > product.getPCount()) {
            throw new NotEnoughtProductException(product.getName());
        }
        productService.updateProductCount(sProductCount, product);
        return salesRepository.save(Sale.builder()
                .sProductCount(sProductCount)
                .finalCost(sProductCount * product.getPBasePrice())
                .client(client)
                .product(product)
                .sTime(LocalDateTime.now())
                .build());
    }

    public Sale addSaleAndClient (Integer sProductCount, String name, String surname, String email, String city, String street, Integer street_number, Product product) {
        if (sProductCount > product.getPCount()) {
            throw new NotEnoughtProductException(product.getName());
        }
        return salesRepository.save(Sale.builder()
                .sProductCount(sProductCount)
                .finalCost(sProductCount * product.getPBasePrice())
                .client(clientRepository.save(Client.builder()
                        .address(Address.builder()
                                        .street_number(street_number)
                                        .street(street)
                                        .city(city)
                                        .build())
                        .cSurname(surname)
                        .email(email)
                        .cName(name)
                        .build()))
                .product(product)
                .sTime(LocalDateTime.now())
                .build());
    }
}
