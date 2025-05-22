package org.uniara.PeopleService.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document
@Getter @Setter
public class Person {
    @Id
    private String id;
    private String name;
    private String cpf;
    private String phoneNumber;
    private Date createdAt;
}
