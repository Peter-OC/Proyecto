package com.capgemini.application.resources;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capgemini.application.dtos.UserDetailsDTO;
import com.capgemini.application.dtos.UserEditDTO;
import com.capgemini.application.dtos.UserShortDTO;
import com.capgemini.domains.contracts.services.UserService;
import com.capgemini.exceptions.DuplicateKeyException;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.exceptions.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/user")
@Api(value = "/User", description = "Mantenimiento de usuarios", produces = "application/json, application/xml", consumes = "application/json, application/xml")
public class UserResource {
	@Autowired
	private UserService srv;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	@ApiOperation(value = "Listado de usuarios")
	public List<UserShortDTO> getAll() {
		return srv.getAll().stream().map(User -> UserShortDTO.from(User)).toList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(params = "page")
	@ApiOperation(value = "Listado paginable de usuarios")
	public Page<UserShortDTO> getAll(@ApiParam(required = false) Pageable page) {
		var content = srv.getAll(page);
		return new PageImpl(content.getContent().stream().map(item -> UserShortDTO.from(item)).toList(), page,
				content.getTotalElements());

	}

	@GetMapping(path = "/{id}")
	public UserDetailsDTO getOneDetails(@PathVariable String username,
			@RequestParam(required = false, defaultValue = "details") String mode) throws NotFoundException {
		return UserDetailsDTO.from(srv.getOne(username));
	}

	@GetMapping(path = "/{id}", params = "mode=edit")
	@ApiOperation(value = "Recupera un usuario")
	@ApiResponses({ @ApiResponse(code = 200, message = "usuario encontrado"),
			@ApiResponse(code = 404, message = "usuario no encontrado") })

	public UserEditDTO getOneEdit(@ApiParam(value = "Identificador del usuario") @PathVariable String username,
			@ApiParam(value = "Versión completa o editable", required = false, allowableValues = "details,edit") @RequestParam(required = true) String mode)
			throws NotFoundException {
		return UserEditDTO.from(srv.getOne(username));
	}

	@PostMapping
	@Transactional
	@ApiOperation(value = "Añadir un nuevo usuario")
	@ApiResponses({ @ApiResponse(code = 201, message = "usuario añadido"),
			@ApiResponse(code = 400, message = "Error al validar los datos o clave duplicada"),
			@ApiResponse(code = 404, message = "usuario no encontrado") })
	public ResponseEntity<Object> create(@Valid @RequestBody UserEditDTO item)
			throws InvalidDataException, DuplicateKeyException, NotFoundException {
		var entity = UserEditDTO.from(item);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		entity = srv.add(entity);
		item.update(entity);
		srv.change(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getUsername()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@Transactional
	@ApiOperation(value = "Modificar un usuario existente", notes = "Los identificadores deben coincidir")
	@ApiResponses({ @ApiResponse(code = 201, message = "usuario añadido"),
			@ApiResponse(code = 400, message = "Error al validar los datos o discrepancias en los identificadores"),
			@ApiResponse(code = 404, message = "usuario no encontrado") })
	public void update(@ApiParam(value = "Identificador del usuario") @PathVariable String username,
			@Valid @RequestBody UserEditDTO item) throws InvalidDataException, NotFoundException {
		if (username != item.getUsername())
			throw new InvalidDataException("No coinciden los identificadores");
		var entity = srv.getOne(username);
		item.update(entity);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		srv.change(entity);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Borrar usuario existente")
	@ApiResponses({ @ApiResponse(code = 204, message = "usuario borrado"),
			@ApiResponse(code = 404, message = "usuario no encontrado") })
	public void delete(@ApiParam(value = "Identificador del usuario") @PathVariable String username) {
		srv.deleteById(username);
	}

}
