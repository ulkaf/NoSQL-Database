package com.plantStore.services;

import com.plantStore.tables.Plate;
import com.plantStore.tables.Product;
import com.plantStore.tables.Seed;
import com.plantStore.exceptions.*;
import com.plantStore.repositories.ProductRepository;

import java.util.UUID;


public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findProductByIdIfExist (UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new productNotExistById(id));
    }

    public void deleteProduct(UUID id) {
        productRepository.delete(findProductByIdIfExist(id));
    }

    public Product findProductByNameIfExist (String name) {
        return productRepository.findProductByName(name).orElseThrow(() -> new productNotExistByName(name));
    }

    public boolean productExists(String name){
        return productRepository.existsByName(name);
    }


    public void updateProductCount (Integer soldCount, Product product) {
        if (findProductByNameIfExist(product.getName()).getPCount() - soldCount < 0)
            throw new NotEnoughtProductException(product.getName());
            updateProduct(product.getId(), product.getName(), product.getPCount() - soldCount, product.getPBasePrice());
    }

    public void addProductCount (Integer addCount, Product product) {
            updateProduct(product.getId(), product.getName(), product.getPCount() + addCount, product.getPBasePrice());
    }

    public void updateProductBasePrice (Double basePrice, Product product ) {
            updateProduct(product.getId(), product.getName(), product.getPCount(), basePrice);
    }

    public void deleteAll() {
        if (!productRepository.findAll().isEmpty())
            productRepository.deleteAll();
    }

    public Product addProduct(Double base_price, Integer count, String name) {
        if (productExists(name))
            throw new productAlreadyExist(name);
        return productRepository.save(Product.builder()
                .id(UUID.randomUUID())
                .name(name)
                .pBasePrice(base_price)
                .pCount(count)
                .build());
    }

    public Product updateProduct(UUID id, String name, Integer pCount, Double basePrice) {
        if (productExists(name)) {
            return productRepository.save(Product.builder()
                    .id(id)
                    .name(name)
                    .pBasePrice(basePrice)
                    .pCount(pCount)
                    .build());
        }
        else
            throw new productNotExistByName(name);
    }



}
