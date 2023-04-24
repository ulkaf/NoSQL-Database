package com.plantStore.documents;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sales")
public class Sale {

    @Id
    private String id;

    @NotNull
    private Integer sProductCount;

    @NotNull
    private Double finalCost;

    @NotNull
    private LocalDateTime sTime;

    @NotNull
    private Client client;

    @NotNull
    private Product product;

}
