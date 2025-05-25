package org.uniara.PeopleService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "People API - Minha Veterinária", description = "API to manage people such as employees, owner of animal, technical", version = "V0"))
public class PeopleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeopleServiceApplication.class, args);
	}

}
