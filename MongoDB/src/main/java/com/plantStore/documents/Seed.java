package com.plantStore.documents;

import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
@TypeAlias("seed")
public class Seed extends Product {

    @Setter
    private Integer weight;

    @Builder
    public Seed(String id, Double pBasePrice, Integer pCount, String pName, Integer weight) {
        super(id, pBasePrice, pCount, pName);
        this.weight = weight;
    }
}
