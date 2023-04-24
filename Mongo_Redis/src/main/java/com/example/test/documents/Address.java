package com.example.test.documents;

import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

    @Setter
    private String street;

    @Setter
    private Integer street_number;

    @Setter
    private String city;

}
