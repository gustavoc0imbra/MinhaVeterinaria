package org.uniara.ScheduleService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Appointment {
    @Id
    private String id;
    private String animalId;
    private String type;
    private Date date;
    private char status;
    private BigDecimal price;
}
