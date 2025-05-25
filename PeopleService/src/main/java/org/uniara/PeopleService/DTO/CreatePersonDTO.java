package org.uniara.PeopleService.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class CreatePersonDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String name;
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;
    @NotBlank(message = "Telefone é obrigatório")
    private String phoneNumber;
}
