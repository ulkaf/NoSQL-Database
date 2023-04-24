package com.plantStore.documents;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Setter
    private String street;

    @Setter
    private Integer street_number;

    @Setter
    private String city;

}
