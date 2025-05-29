package org.uniara.ScheduleService.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Getter @Setter
public class UpdateAppointmentDTO {
    @NotBlank(message = "ID é obrigatório")
    private String id;
    @NotBlank(message = "Animal é obrigatório")
    private String animalId;
    @NotBlank(message = "Tipo de serviço é obrigatório")
    private String type;
    @NotBlank(message = "Data do serviço é obrigatória")
    private Date date;
    private char status;
    @NotBlank(message = "Preço é obrigatório")
    private BigDecimal price;
}
