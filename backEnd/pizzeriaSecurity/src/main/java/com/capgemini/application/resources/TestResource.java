package com.capgemini.application.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.trace.http.HttpTrace.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestResource {
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/seguro")
	public String get(@RequestHeader String authorization, Principal principal) {
		return "El usuario está autenticado: " + principal.getName() + "\nAutorization: " + authorization;
	}
	
	@GetMapping("/pass")
	public String getPass(String pass) {
		return passwordEncoder.encode(pass);
	}
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "El usuario es administrador";
	}
	
	@Value("${jwt.secret}")
	private String SECRET;
	
	@GetMapping("/secreto")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String getSecreto() {
		return SECRET;
	}
}
