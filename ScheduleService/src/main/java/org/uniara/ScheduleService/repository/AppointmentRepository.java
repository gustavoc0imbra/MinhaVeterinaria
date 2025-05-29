package org.uniara.ScheduleService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.uniara.ScheduleService.model.Appointment;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
}
