package org.uniara.AnimalService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uniara.AnimalService.model.Animal;
import org.uniara.AnimalService.repository.AnimalRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    public Animal save(Animal animal) {
        return animalRepository.save(animal);
    }

    public Optional<Animal> findById(String id) {
        return animalRepository.findById(id);
    }

    public void deleteById(String id) {
        animalRepository.deleteById(id);
    }
}
