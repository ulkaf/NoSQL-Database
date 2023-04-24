package com.plantStore.services;

import com.plantStore.documents.Plate;
import com.plantStore.documents.Product;
import com.plantStore.documents.Seed;
import com.plantStore.exceptions.*;
import com.plantStore.repositories.ProductRepository;


public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findProductByIdIfExist (String id) {
        return productRepository.findById(id).orElseThrow(() -> new productNotExistById(id));
    }

    public void deleteProduct(String id) {
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
        if (product.getClass() == Seed.class)
            updateSeed(product.getId(), product.getName(), product.getPCount() - soldCount, product.getPBasePrice());
        if (product.getClass() == Plate.class)
            updatePlate(product.getId(), product.getName(), product.getPCount() - soldCount, product.getPBasePrice());
    }

    public void addProductCount (Integer addCount, Product product) {
        if (product.getClass() == Seed.class)
            updateSeed(product.getId(), product.getName(), product.getPCount() + addCount, product.getPBasePrice());
        if (product.getClass() == Plate.class)
            updatePlate(product.getId(), product.getName(), product.getPCount() + addCount, product.getPBasePrice());
    }

    public void updateProductBasePrice (Double basePrice, Product product ) {
        if (product.getClass() == Seed.class)
            updateSeed(product.getId(), product.getName(), product.getPCount(), basePrice);
        if (product.getClass() == Plate.class)
            updatePlate(product.getId(), product.getName(), product.getPCount(), basePrice);
    }

    public void deleteAll() {
        if (!productRepository.findAll().isEmpty())
            productRepository.deleteAll();
    }

    public Product addSeed(Double base_price, Integer count, String name, Integer weight) {
        if (productExists(name))
            throw new productAlreadyExist(name);
        return productRepository.save(Seed.builder()
                .pName(name)
                .pBasePrice(base_price)
                .pCount(count)
                .weight(weight)
                .build());
    }


    public Product addPlate(Double base_price, Integer count, String name, Integer height, String period ) {
        if (productExists(name))
            throw new productAlreadyExist(name);
        return productRepository.save(Plate.builder()
                .pName(name)
                .pBasePrice(base_price)
                .pCount(count)
                .height(height)
                .period(period)
                .build());
    }

    public Product updatePlate(String id, String name, Integer pCount, Double basePrice) {
        if (productExists(name)) {
            return productRepository.save(Plate.builder()
                    .id(id)
                    .pName(name)
                    .pBasePrice(basePrice)
                    .pCount(pCount)
                    .build());
        }
        else
            throw new productNotExistByName(name);
    }

    public Product updateSeed(String id, String name, Integer pCount, Double basePrice) {
        if (productExists(name)) {
            return productRepository.save(Seed.builder()
                    .id(id)
                    .pName(name)
                    .pBasePrice(basePrice)
                    .pCount(pCount)
                    .build());
        }
        else
            throw new productNotExistByName(name);
    }
}
