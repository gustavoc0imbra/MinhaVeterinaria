package org.uniara.AnimalService.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class CreateAnimalDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String name;
    @NotBlank(message = "Espécie é obrigatório")
    private String specie;
    @NotBlank(message = "Raça é obrigatório")
    private String breed;
    @NotBlank(message = "Idade é obrigatório")
    private int age;
}
