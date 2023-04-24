package com.plantStore.tables;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Frozen;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(value = "sale")
public class Sale {

    @PrimaryKey
    private UUID id;

    @Column(value = "sProductCount")
    private Integer sProductCount;

    @Column(value = "finalCost")
    private Double finalCost;

    @Column(value = "sTime")
    private LocalDateTime sTime;

    @Column(value = "client")
    private String clientEmail;

    @Column(value = "product")
    private String productName;

}
