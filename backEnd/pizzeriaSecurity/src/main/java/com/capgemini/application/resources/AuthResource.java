package com.capgemini.application.resources;

import java.io.Console;
import java.net.URI;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capgemini.application.dtos.AuthToken;
import com.capgemini.application.dtos.BasicCredential;
import com.capgemini.application.dtos.FunctionEditDTO;
import com.capgemini.application.dtos.ProfileDTO;
import com.capgemini.application.dtos.UserCreateDTO;
import com.capgemini.application.dtos.UserEditDTO;
import com.capgemini.domains.contracts.services.UserService;
import com.capgemini.domains.entities.User;
import com.capgemini.exceptions.DuplicateKeyException;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.exceptions.NotFoundException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
		return ResponseEntity.ok(new AuthToken(true, getJWTToken(usr), username, usr.getFunction()));		
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
	
	@PostMapping(path="/register")
	@Transactional
	@ApiOperation(value = "Añadir un nuevo usuario")
	@ApiResponses({ @ApiResponse(code = 201, message = "usuario añadido"),
			@ApiResponse(code = 400, message = "Error al validar los datos o clave duplicada"),
			@ApiResponse(code = 404, message = "usuario no encontrado") })
	public ResponseEntity<Object> create(@Valid @RequestBody UserCreateDTO item)
			throws InvalidDataException, DuplicateKeyException, NotFoundException {
		var entity = UserCreateDTO.from(item);
		// validar password
		entity.setPassword(passwordEncoder.encode(item.getPassword()));
		//entity.setFunction("ROLE_USER");
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		entity = srv.add(entity);
		item.update(entity);
		srv.change(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getUsername()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping(path = "/password")
	@Transactional
	public ResponseEntity<Object> password(@RequestParam String oldPassword, @RequestParam String newPassword, Principal identity) throws NotFoundException, InvalidDataException {		
		User usr;
		try {
			usr = srv.getOne(identity.getName());
			if(!passwordEncoder.matches(oldPassword, usr.getPassword()))
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();	
		}
		usr.setPassword(passwordEncoder.encode(newPassword));
		srv.change(usr);
		return ResponseEntity.accepted().build();		
	}
	
	
	@GetMapping(path = "/profile")
	@ApiOperation(value = "Recupera un usuario")
	@ApiResponses({ @ApiResponse(code = 200, message = "usuario encontrado"),
			@ApiResponse(code = 404, message = "usuario no encontrado") })
	public ProfileDTO getProfile(Principal identity)
			throws NotFoundException {
		return ProfileDTO.from(srv.getOne(identity.getName()));
	}

	@PutMapping(path = "/profile")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateProfile(@Valid @RequestBody ProfileDTO item, Principal identity) throws InvalidDataException, NotFoundException {
		var entity = srv.getOne(identity.getName());
		entity.setAddress(item.getAddress());
		entity.setFirstName(item.getFirst_name());
		entity.setLastName(item.getLast_name());
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		srv.change(entity);
	}

}

/*
 * /register (anonimo)
 * /Changepassword
 * /profile (Autentication) (get, put) modificar sus datos menos la contraseña CRUD ---
 * /users (Admin)  (get, getAll, post, put, delete) + roles 
*/