package com.capgemini.application.resources;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.application.dtos.AuthToken;
import com.capgemini.application.dtos.BasicCredential;
import com.capgemini.domains.contracts.services.UserService;
import com.capgemini.domains.entities.User;
import com.capgemini.exceptions.NotFoundException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
//	@CrossOrigin(origins = "http://localhost:4200", allowCredentials="true", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS })
//	@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials="false")
public class AuthResource {
	@Value("${jwt.secret}")
	private String SECRET;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserService srv;

	@RequestMapping(path = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<AuthToken> login(@RequestParam("name") String username, @RequestParam("password") String pwd) {		
		User usr;
		try {
			usr = srv.getOne(username);
			if(!passwordEncoder.matches(pwd, usr.getPassword()))
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();	
		}
		return ResponseEntity.ok(new AuthToken(true, getJWTToken(usr), username));		
	}
	
	@PostMapping(path = "/login", consumes = "application/json")
	public ResponseEntity<AuthToken> loginPostJSON(@RequestBody BasicCredential usr) {		
		return login(usr.getUsername(), usr.getPassword());		
	}

	private String getJWTToken(User usr) {
		List<GrantedAuthority> grantedAuthorities = usr.getFunction().contains(",") ? AuthorityUtils.commaSeparatedStringToAuthorityList(usr.getFunction()) :
				AuthorityUtils.createAuthorityList(usr.getFunction())	;	
		String token = Jwts.builder()
				.setId("MicroserviciosJWT")
				.setSubject(usr.getUsername())//Aqui user get.y lo que usemos de nombre de usuario
				.claim("authorities",
					grantedAuthorities.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						SECRET.getBytes()).compact();
		return "Bearer " + token;
	}
}

/*
 * /register (anonimo)
 * /Changepassword
 * /profile (Autentication) (get, put) modificar sus datos menos la contraseña
 * /users (Admin)  (get, getAll, post, put, delete) + roles 
*/