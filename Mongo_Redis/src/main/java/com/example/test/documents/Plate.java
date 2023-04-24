package com.example.test.documents;

import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
@TypeAlias("plate")
public class Plate extends Product{
    @Setter
    private Integer height;

    @Setter
    private String period;

    @Builder
    public Plate(String id, Double pBasePrice, Integer pCount, String pName, Integer height, String period) {
        super(id, pBasePrice, pCount, pName);
        this.height = height;
        this.period = period;
    }
}
