package com.example.kafka_nbd.documents;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

    private String street;

    private Integer street_number;

    private String city;

}
