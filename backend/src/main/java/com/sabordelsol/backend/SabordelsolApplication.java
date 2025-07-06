package com.sabordelsol.backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SabordelsolApplication {

	public static void main(String[] args) {
		// Load environment variables from .env file
		Dotenv dotenv = Dotenv.configure()
				.directory("./")
				.ignoreIfMissing()
				.load();
		
		// Set system properties from .env file
		System.setProperty("DB_NAME", dotenv.get("DB_NAME", ""));
		System.setProperty("DB_USER", dotenv.get("DB_USER", ""));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD", ""));
		
		SpringApplication.run(SabordelsolApplication.class, args);
	}

}
