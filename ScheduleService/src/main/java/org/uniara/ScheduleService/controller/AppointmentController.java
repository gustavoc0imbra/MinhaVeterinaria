package org.uniara.ScheduleService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.uniara.ScheduleService.DTO.AppointmentAnimalDTO;
import org.uniara.ScheduleService.DTO.CreateAppointmentDTO;
import org.uniara.ScheduleService.DTO.ResponseAppointmentDTO;
import org.uniara.ScheduleService.DTO.UpdateAppointmentDTO;
import org.uniara.ScheduleService.constant.Constant;
import org.uniara.ScheduleService.consumers.AnimalConsumer;
import org.uniara.ScheduleService.consumers.AuthConsumer;
import org.uniara.ScheduleService.model.Appointment;
import org.uniara.ScheduleService.service.AppointmentService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    private AuthConsumer authConsumer = new AuthConsumer();

    private AnimalConsumer animalConsumer = new AnimalConsumer();

    /*
    * TODO
    * Find way to fill the person field in findAll method efficiently
    *
    * */

    @GetMapping(Constant.API_SCHEDULE)
    public ResponseEntity<List<Appointment>> findAll(@RequestHeader("Authorization") String token) {

        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(appointmentService.findAll(token));
    }

    @PostMapping(Constant.API_SCHEDULE)
    public ResponseEntity<ResponseAppointmentDTO> save(@RequestHeader("Authorization") String token, @RequestBody CreateAppointmentDTO dto) {

        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        AppointmentAnimalDTO animal = null;

        if (!dto.getAnimalId().isEmpty()) {
            animal = animalConsumer.getById(token, dto.getAnimalId());
        }

        if (animal == null || animal.getId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal não encontrado");
        }

        Appointment appointment = new Appointment();
        appointment.setAnimalId(animal.getId());
        appointment.setType(dto.getType());
        appointment.setDate(dto.getDate());
        appointment.setStatus('P');
        appointment.setPrice(dto.getPrice());

        appointment = appointmentService.save(appointment);

        ResponseAppointmentDTO response = new ResponseAppointmentDTO(appointment, animal);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(Constant.API_SCHEDULE)
    public ResponseEntity<ResponseAppointmentDTO> update(@RequestHeader("Authorization") String token, @RequestBody UpdateAppointmentDTO dto) {
        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Appointment> appointment = appointmentService.findById(dto.getId());

        if (appointment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AppointmentAnimalDTO animal = null;

        if (!dto.getAnimalId().isEmpty()) {
            animal = animalConsumer.getById(token, dto.getAnimalId());
        }

        if (animal == null || animal.getId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal não encontrado");
        }

        appointment.get().setAnimalId(animal.getId());
        appointment.get().setType(dto.getType());
        appointment.get().setDate(dto.getDate());
        appointment.get().setStatus(dto.getStatus());
        appointment.get().setPrice(dto.getPrice());

        Appointment updatedAppointment = appointmentService.save(appointment.get());

        ResponseAppointmentDTO response = new ResponseAppointmentDTO(updatedAppointment, animal);

        return ResponseEntity.ok(response);
    }

    @GetMapping(Constant.API_SCHEDULE + "/{id}")
    public ResponseEntity<ResponseAppointmentDTO> findById(@RequestHeader("Authorization") String token, @PathVariable("id") String id) {
        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Appointment> appointment = appointmentService.findById(id);

        if (appointment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AppointmentAnimalDTO animal = null;

        if (appointment.get().getAnimalId() != null) {
            animal = animalConsumer.getById(token, appointment.get().getAnimalId());
        }

        ResponseAppointmentDTO response = new ResponseAppointmentDTO(appointment.get(), animal);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(Constant.API_SCHEDULE + "/{id}")
    public ResponseEntity<Appointment> deleteById(@RequestHeader("Authorization") String token, @PathVariable("id") String id) {
        if (!authConsumer.isAuthenticated(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        appointmentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
