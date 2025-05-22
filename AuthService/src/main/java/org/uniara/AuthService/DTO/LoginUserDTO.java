package org.uniara.AuthService.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class LoginUserDTO {
    @NotBlank(message = "O email é obrigatório!")
    private String email;
    @NotBlank(message = "A senha é obrigatória!")
    private String password;
}
