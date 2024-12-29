package com.omerada.cozumcebinde;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.omerada.cozumcebinde.core.security.models")
@EnableJpaRepositories(basePackages = "com.omerada.cozumcebinde.core.security.repository")
public class CozumCebindeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CozumCebindeApiApplication.class, args);
	}

}
