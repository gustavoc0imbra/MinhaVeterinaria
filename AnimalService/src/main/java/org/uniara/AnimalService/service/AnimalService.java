package org.uniara.AnimalService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uniara.AnimalService.DTO.AnimalPersonDTO;
import org.uniara.AnimalService.DTO.ResponseAnimalDTO;
import org.uniara.AnimalService.consumers.PeopleConsumer;
import org.uniara.AnimalService.model.Animal;
import org.uniara.AnimalService.repository.AnimalRepository;

import java.util.*;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    private PeopleConsumer peopleConsumer = new PeopleConsumer();

    public List<ResponseAnimalDTO> findAll(String token) {
        List<Animal> animals = animalRepository.findAll();
        HashMap<String, AnimalPersonDTO> personIds = new HashMap<String, AnimalPersonDTO>();
        List<ResponseAnimalDTO> responseAnimals = new ArrayList<ResponseAnimalDTO>();

        /*animals.stream().map(animal -> (animal.getPersonId() != null | !personIds.containsKey(animal.getPersonId())) ? personIds.put(animal.getPersonId(), null) : null);*/

        for (Animal animal : animals) {
            if (animal.getPersonId() != null && !personIds.containsKey(animal.getPersonId())) {
                personIds.put(animal.getPersonId(), null);
            }
        }

        for (String personId : personIds.keySet()) {
            AnimalPersonDTO person = peopleConsumer.getById(token, personId);
            personIds.put(personId, person);
        }

        for (Animal animal : animals) {
            if(personIds.containsKey(animal.getPersonId())) {
                responseAnimals.add(new ResponseAnimalDTO(animal, personIds.get(animal.getPersonId())));
            }else {
                responseAnimals.add(new ResponseAnimalDTO(animal, null));
            }
        }

        return responseAnimals;
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
