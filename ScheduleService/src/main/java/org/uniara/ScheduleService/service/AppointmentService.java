package org.uniara.ScheduleService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uniara.ScheduleService.DTO.AppointmentAnimalDTO;
import org.uniara.ScheduleService.DTO.ResponseAppointmentDTO;
import org.uniara.ScheduleService.consumers.AnimalConsumer;
import org.uniara.ScheduleService.model.Appointment;
import org.uniara.ScheduleService.repository.AppointmentRepository;

import java.util.*;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    private AnimalConsumer animalConsumer = new AnimalConsumer();

    public List<ResponseAppointmentDTO> findAll(String token) {
        List<Appointment> appointments = appointmentRepository.findAll();
        HashMap<String, AppointmentAnimalDTO> animalsIds = new HashMap<String, AppointmentAnimalDTO>();
        List<ResponseAppointmentDTO> responseAnimals = new ArrayList<ResponseAppointmentDTO>();

        /*animals.stream().map(animal -> (animal.getPersonId() != null | !personIds.containsKey(animal.getPersonId())) ? personIds.put(animal.getPersonId(), null) : null);*/

        for (Appointment appointment : appointments) {
            if (appointment.getAnimalId() != null && !animalsIds.containsKey(appointment.getAnimalId())) {
                animalsIds.put(appointment.getAnimalId(), null);
            }
        }

        for (String animalId : animalsIds.keySet()) {
            AppointmentAnimalDTO person = animalConsumer.getById(token, animalId);
            animalsIds.put(animalId, person);
        }

        for (Appointment appointment : appointments) {
            if(animalsIds.containsKey(appointment.getAnimalId())) {
                responseAnimals.add(new ResponseAppointmentDTO(appointment, animalsIds.get(appointment.getAnimalId())));
            }else {
                responseAnimals.add(new ResponseAppointmentDTO(appointment, null));
            }
        }

        return responseAnimals;
    }

    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Optional<Appointment> findById(String id) {
        return appointmentRepository.findById(id);
    }

    public void deleteById(String id) {
        appointmentRepository.deleteById(id);
    }
}
