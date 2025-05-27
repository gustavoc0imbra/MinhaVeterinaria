package org.uniara.AnimalService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uniara.AnimalService.DTO.AnimalPersonDTO;
import org.uniara.AnimalService.DTO.CreateAnimalDTO;
import org.uniara.AnimalService.DTO.ResponseAnimalDTO;
import org.uniara.AnimalService.DTO.UpdateAnimalDTO;
import org.uniara.AnimalService.constant.Constant;
import org.uniara.AnimalService.consumers.AuthConsumer;
import org.uniara.AnimalService.consumers.PeopleConsumer;
import org.uniara.AnimalService.model.Animal;
import org.uniara.AnimalService.service.AnimalService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    private AuthConsumer authConsumer = new AuthConsumer();

    private PeopleConsumer peopleConsumer = new PeopleConsumer();

    /*
    * TODO
    * Find way to fill the person field in findAll method efficiently
    *
    * */

    @GetMapping(Constant.API_ANIMAL)
    public ResponseEntity<List<ResponseAnimalDTO>> findAll(@RequestHeader("Authorization") String token) {

        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(animalService.findAll(token));
    }

    @PostMapping(Constant.API_ANIMAL)
    public ResponseEntity<ResponseAnimalDTO> save(@RequestHeader("Authorization") String token, @RequestBody CreateAnimalDTO dto) {

        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        AnimalPersonDTO person = null;

        if (!dto.getPersonId().isEmpty()) {
            System.out.println("Buscou pessoa " + dto.getPersonId());
            person = peopleConsumer.getById(token, dto.getPersonId());
        }

        Animal animal = new Animal();

        if (person != null) {
            animal.setPersonId(person.getId() != null ? person.getId() : null);
        }

        animal.setName(dto.getName());
        animal.setSpecie(dto.getSpecie());
        animal.setBreed(dto.getBreed());
        animal.setAge(dto.getAge());

        animal = animalService.save(animal);

        ResponseAnimalDTO response = new ResponseAnimalDTO(animal, person);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(Constant.API_ANIMAL)
    public ResponseEntity<ResponseAnimalDTO> update(@RequestHeader("Authorization") String token, @RequestBody UpdateAnimalDTO dto) {
        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Animal> animal = animalService.findById(dto.getId());

        if (animal.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AnimalPersonDTO person = null;

        if (dto.getPersonId() != null) {
            person = peopleConsumer.getById(token, dto.getPersonId());
        }

        animal.get().setPersonId(person.getId() != null ? person.getId() : null);
        animal.get().setName(dto.getName());
        animal.get().setSpecie(dto.getSpecie());
        animal.get().setBreed(dto.getBreed());
        animal.get().setAge(dto.getAge());

        Animal updatedAnimal = animalService.save(animal.get());

        ResponseAnimalDTO response = new ResponseAnimalDTO(updatedAnimal, person);

        return ResponseEntity.ok(response);
    }

    @GetMapping(Constant.API_ANIMAL + "/{id}")
    public ResponseEntity<ResponseAnimalDTO> findById(@RequestHeader("Authorization") String token, @PathVariable("id") String id) {
        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Animal> animal = animalService.findById(id);

        if (animal.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AnimalPersonDTO person = null;

        if (animal.get().getPersonId() != null) {
            person = peopleConsumer.getById(token, animal.get().getPersonId());
        }

        ResponseAnimalDTO response = new ResponseAnimalDTO(animal.get(), person);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(Constant.API_ANIMAL + "/{id}")
    public ResponseEntity<Animal> deleteById(@RequestHeader("Authorization") String token, @PathVariable("id") String id) {
        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        animalService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
