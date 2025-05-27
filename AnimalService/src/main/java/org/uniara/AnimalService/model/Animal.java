package org.uniara.AnimalService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Animal {
    @Id
    private String id;
    private String personId;
    private String name;
    private String specie;
    private String breed;
    private int age;
}
