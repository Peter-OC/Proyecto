package com.capgemini.application.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.application.dtos.UserShortDTO;
import com.capgemini.domains.contracts.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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

//	@GetMapping(path = "/{id}")
//	public IngredientDetailsDTO getOneDetails(@PathVariable int id,
//			@RequestParam(required = false, defaultValue = "details") String mode) throws NotFoundException {
//		return IngredientDetailsDTO.from(srv.getOne(id));
//	}

//	@GetMapping(path = "/{id}", params = "mode=edit")
//	@ApiOperation(value = "Recupera un alquiler")
//	@ApiResponses({ @ApiResponse(code = 200, message = "Alquiler encontrado"),
//			@ApiResponse(code = 404, message = "Alquiler no encontrado") })
//
//	public IngredientEditDTO getOneEdit(@ApiParam(value = "Identificador del alquiler") @PathVariable int id,
//			@ApiParam(value = "Versión completa o editable", required = false, allowableValues = "details,edit") @RequestParam(required = true) String mode)
//			throws NotFoundException {
//		return IngredientEditDTO.from(srv.getOne(id));
//	}

//	@PostMapping
//	@Transactional
//	@ApiOperation(value = "Añadir un nuevo alquiler")
//	@ApiResponses({ @ApiResponse(code = 201, message = "Aluiler añadido"),
//			@ApiResponse(code = 400, message = "Error al validar los datos o clave duplicada"),
//			@ApiResponse(code = 404, message = "Alquiler no encontrado") })
//	public ResponseEntity<Object> create(@Valid @RequestBody IngredientEditDTO item)
//			throws InvalidDataException, DuplicateKeyException, NotFoundException {
//		var entity = IngredientEditDTO.from(item);
//		if (entity.isInvalid())
//			throw new InvalidDataException(entity.getErrorsMessage());
//		entity = srv.add(entity);
//		item.update(entity);
//		srv.change(entity);
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(entity.getIngredientId()).toUri();
//		return ResponseEntity.created(location).build();
//
//	}

//	@PutMapping("/{id}")
//	@ResponseStatus(HttpStatus.ACCEPTED)
//	@Transactional
//	@ApiOperation(value = "Modificar un alquiler existente", notes = "Los identificadores deben coincidir")
//	@ApiResponses({ @ApiResponse(code = 201, message = "Alquiler añadido"),
//			@ApiResponse(code = 400, message = "Error al validar los datos o discrepancias en los identificadores"),
//			@ApiResponse(code = 404, message = "Alquiler no encontrado") })
//	public void update(@ApiParam(value = "Identificador del alquiler") @PathVariable int id,
//			@Valid @RequestBody IngredientEditDTO item) throws InvalidDataException, NotFoundException {
//		if (id != item.getIngredientId())
//			throw new InvalidDataException("No coinciden los identificadores");
//		var entity = srv.getOne(id);
//		item.update(entity);
//		if (entity.isInvalid())
//			throw new InvalidDataException(entity.getErrorsMessage());
//		srv.change(entity);
//	}
//
//	@DeleteMapping("/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	@ApiOperation(value = "Borrar un alquiler existente")
//	@ApiResponses({ @ApiResponse(code = 204, message = "Alquiler borrado"),
//			@ApiResponse(code = 404, message = "Alquiler no encontrado") })
//	public void delete(@ApiParam(value = "Identificador del alquiler") @PathVariable int id) {
//		srv.deleteById(id);
//	}

}
