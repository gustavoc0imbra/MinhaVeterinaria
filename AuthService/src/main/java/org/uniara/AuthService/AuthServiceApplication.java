package org.uniara.AuthService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Auth/User API - Minha Veterinária", description = "API to authenticate and manage users from system", version = "V0"))
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
