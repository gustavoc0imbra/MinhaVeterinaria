package org.uniara.ScheduleService.DTO;

import lombok.Getter;
import org.uniara.ScheduleService.model.Appointment;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class ResponseAppointmentDTO {
    private String id;
    private AppointmentAnimalDTO animal;
    private String type;
    private Date date;
    private char status;
    private BigDecimal price;

    public ResponseAppointmentDTO(Appointment appointment, AppointmentAnimalDTO animal) {
        this.id = appointment.getId();
        this.animal = animal;
        this.type = appointment.getType();
        this.date = appointment.getDate();
        this.status = appointment.getStatus();
        this.price = appointment.getPrice();
    }
}
