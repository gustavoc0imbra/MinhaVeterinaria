package org.uniara.AnimalService.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter @Setter
public class Animal {
    @Id
    private String id;
    private String name;
    private String specie;
    private String breed;
    private int age;
}
