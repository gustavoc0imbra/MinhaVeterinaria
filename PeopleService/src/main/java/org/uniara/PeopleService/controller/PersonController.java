package org.uniara.PeopleService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uniara.PeopleService.DTO.CreatePersonDTO;
import org.uniara.PeopleService.DTO.UpdatePersonDTO;
import org.uniara.PeopleService.constant.Constant;
import org.uniara.PeopleService.model.Person;
import org.uniara.PeopleService.service.PersonService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(Constant.API_PEOPLE)
    public ResponseEntity<List<Person>> findAll(/*@RequestHeader("Authorization") String token*/) {
        return ResponseEntity.ok(personService.findAll());
    }

    @PostMapping(Constant.API_PEOPLE)
    public ResponseEntity<Person> save(/*@RequestHeader("Authorization") String token, */@RequestBody CreatePersonDTO dto) {
        Person person = new Person();

        if (person.getCreatedAt() == null) {
            person.setCreatedAt(new Date());
        }

        person.setName(dto.getName());
        person.setPhoneNumber(dto.getPhoneNumber());

        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(person));
    }

    @PutMapping(Constant.API_PEOPLE)
    public ResponseEntity<Person> update(/*@RequestHeader("Authorization") String token, */@RequestBody UpdatePersonDTO dto) {
        Optional<Person> customer = personService.findById(dto.getId());

        if (customer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        customer.get().setName(dto.getName());
        customer.get().setPhoneNumber(dto.getPhoneNumber());

        return ResponseEntity.ok(personService.save(customer.get()));
    }

    @GetMapping(Constant.API_PEOPLE + "/{id}")
    public ResponseEntity<Optional<Person>> findById(/*@RequestHeader("Authorization") String token, */@PathVariable("id") String id) {
        return ResponseEntity.ok(personService.findById(id));
    }

    @DeleteMapping(Constant.API_PEOPLE + "/{id}")
    public ResponseEntity<Person> deleteById(/*@RequestHeader("Authorization") String token, */@PathVariable("id") String id) {
        personService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
