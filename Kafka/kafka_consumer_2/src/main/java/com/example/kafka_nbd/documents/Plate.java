package com.example.kafka_nbd.documents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
@TypeAlias("plate")
@JsonTypeName("plate")
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

    @Override
    public String toString() {
        return "Plate{" +
                "id='" + super.getId() + '\'' +
                ", pBasePrice=" + super.getPBasePrice() +
                ", pCount=" + super.getPCount() +
                ", name='" + super.getName() + '\'' +
                "height=" + height +
                ", period='" + period + '\'' +
                '}';
    }
}
