package com.example.test.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "clients")
public class Client implements Serializable {

    @Id
    private String id;

    private String cName;

    @Setter
    private String cSurname;

    @Setter
    @Indexed(name = "email_text", unique = true)
    private String email;

    @Setter
    private Address address;

}
