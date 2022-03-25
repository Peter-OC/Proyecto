package com.capgemini;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PizzeriaSecurityApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(PizzeriaSecurityApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("SERVICIO SPRING LEVANTADO CON EXITO");
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
} 
