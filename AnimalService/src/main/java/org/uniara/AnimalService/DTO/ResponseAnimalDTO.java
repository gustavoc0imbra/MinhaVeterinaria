package org.uniara.AnimalService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.uniara.AnimalService.model.Animal;

@Getter
public class ResponseAnimalDTO {
    private String id;
    private AnimalPersonDTO person;
    private String name;
    private String specie;
    private String breed;
    private int age;

    public ResponseAnimalDTO(Animal animal, AnimalPersonDTO person) {
        this.id = animal.getId();
        this.person = person;
        this.name = animal.getName();
        this.specie = animal.getSpecie();
        this.breed = animal.getBreed();
        this.age = animal.getAge();
    }
}
