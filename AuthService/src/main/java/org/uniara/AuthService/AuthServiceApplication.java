package org.uniara.AuthService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.uniara.AuthService.model.User;
import org.uniara.AuthService.repository.UserRepository;
import org.uniara.AuthService.service.UserService;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Auth/User API - Minha Veterinária", description = "API to authenticate and manage users from system", version = "V0"))
public class AuthServiceApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (userRepository.count() == 0) {
			System.out.println("Creating default users...");
			User user = new User();

			user.setUsername("admin");
			user.setEmail("admin@email.com");
			user.setPassword("admin123");

			userRepository.save(user);
			System.out.println("Default users created!");
		}
	}
}
