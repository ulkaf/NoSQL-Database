package com.example.kafka_nbd.documents;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "clients")
public class Client {

    @Id
    private String id;

    @NotNull
    private String cName;


    @NotNull
    private String cSurname;


    @NotNull
    @Indexed(name = "email_text", unique = true)
    private String email;

    @NotNull
    private Address address;

}
