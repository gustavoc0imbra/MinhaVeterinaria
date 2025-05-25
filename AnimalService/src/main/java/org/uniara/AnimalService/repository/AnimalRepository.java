package org.uniara.AnimalService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.uniara.AnimalService.model.Animal;

@Repository
public interface AnimalRepository extends MongoRepository<Animal, String> {
}
