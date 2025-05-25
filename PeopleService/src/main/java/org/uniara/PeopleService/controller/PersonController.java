package org.uniara.PeopleService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uniara.PeopleService.DTO.CreatePersonDTO;
import org.uniara.PeopleService.DTO.UpdatePersonDTO;
import org.uniara.PeopleService.constant.Constant;
import org.uniara.PeopleService.consumers.AuthConsumer;
import org.uniara.PeopleService.model.Person;
import org.uniara.PeopleService.service.PersonService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    private AuthConsumer authConsumer = new AuthConsumer();

    @GetMapping(Constant.API_PEOPLE)
    public ResponseEntity<List<Person>> findAll(@RequestHeader("Authorization") String token) {

        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(personService.findAll());
    }

    @PostMapping(Constant.API_PEOPLE)
    public ResponseEntity<Person> save(@RequestHeader("Authorization") String token, @RequestBody CreatePersonDTO dto) {

        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Person person = new Person();

        if (person.getCreatedAt() == null) {
            person.setCreatedAt(new Date());
        }

        person.setName(dto.getName());
        person.setCpf(dto.getCpf());
        person.setPhoneNumber(dto.getPhoneNumber());

        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(person));
    }

    @PutMapping(Constant.API_PEOPLE)
    public ResponseEntity<Person> update(@RequestHeader("Authorization") String token, @RequestBody UpdatePersonDTO dto) {
        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Person> person = personService.findById(dto.getId());

        if (person.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        person.get().setName(dto.getName());
        person.get().setCpf(dto.getCpf());
        person.get().setPhoneNumber(dto.getPhoneNumber());

        return ResponseEntity.ok(personService.save(person.get()));
    }

    @GetMapping(Constant.API_PEOPLE + "/{id}")
    public ResponseEntity<Optional<Person>> findById(@RequestHeader("Authorization") String token, @PathVariable("id") String id) {
        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(personService.findById(id));
    }

    @DeleteMapping(Constant.API_PEOPLE + "/{id}")
    public ResponseEntity<Person> deleteById(@RequestHeader("Authorization") String token, @PathVariable("id") String id) {
        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        personService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
