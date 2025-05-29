package org.uniara.ScheduleService.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Getter @Setter
public class CreateAppointmentDTO {
    @NotBlank(message = "Tipo de serviço é obrigatório")
    private String type;
    @NotBlank(message = "Animal é obrigatório")
    private String animalId;
    @NotBlank(message = "Data do serviço é obrigatória")
    private Date date;
    @NotBlank(message = "Preço é obrigatório")
    private BigDecimal price;
}
