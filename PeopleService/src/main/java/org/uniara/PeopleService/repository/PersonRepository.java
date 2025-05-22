package org.uniara.PeopleService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.uniara.PeopleService.model.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
}
