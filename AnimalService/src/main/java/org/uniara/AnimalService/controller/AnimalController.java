package org.uniara.AnimalService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uniara.AnimalService.DTO.CreateAnimalDTO;
import org.uniara.AnimalService.DTO.UpdateAnimalDTO;
import org.uniara.AnimalService.constant.Constant;
import org.uniara.AnimalService.consumers.AuthConsumer;
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

    @GetMapping(Constant.API_ANIMAL)
    public ResponseEntity<List<Animal>> findAll(@RequestHeader("Authorization") String token) {

        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(animalService.findAll());
    }

    @PostMapping(Constant.API_ANIMAL)
    public ResponseEntity<Animal> save(@RequestHeader("Authorization") String token, @RequestBody CreateAnimalDTO dto) {

        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Animal animal = new Animal();

        animal.setName(dto.getName());
        animal.setSpecie(dto.getSpecie());
        animal.setBreed(dto.getBreed());
        animal.setAge(dto.getAge());

        return ResponseEntity.status(HttpStatus.CREATED).body(animalService.save(animal));
    }

    @PutMapping(Constant.API_ANIMAL)
    public ResponseEntity<Animal> update(@RequestHeader("Authorization") String token, @RequestBody UpdateAnimalDTO dto) {
        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Animal> animal = animalService.findById(dto.getId());

        if (animal.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        animal.get().setName(dto.getName());
        animal.get().setSpecie(dto.getSpecie());
        animal.get().setBreed(dto.getBreed());
        animal.get().setAge(dto.getAge());

        return ResponseEntity.ok(animalService.save(animal.get()));
    }

    @GetMapping(Constant.API_ANIMAL + "/{id}")
    public ResponseEntity<Optional<Animal>> findById(@RequestHeader("Authorization") String token, @PathVariable("id") String id) {
        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(animalService.findById(id));
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
