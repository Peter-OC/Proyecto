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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capgemini.application.dtos.IngredientDetailsDTO;
import com.capgemini.application.dtos.IngredientEditDTO;
import com.capgemini.application.dtos.IngredientShortDTO;
import com.capgemini.domains.contracts.services.IngredientService;
import com.capgemini.exceptions.DuplicateKeyException;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.exceptions.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/ingredient")
@Api(value = "/Ingredient", description = "Mantenimiento de ingredientes", produces = "application/json, application/xml", consumes = "application/json, application/xml")
public class IngredientResource {
	@Autowired
	private IngredientService srv;

	@GetMapping
	@ApiOperation(value = "Listado de los ingredientes")
	public List<IngredientShortDTO> getAll() {
		return srv.getAll().stream().map(item -> IngredientShortDTO.from(item)).toList();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    @GetMapping(params = "page")
    @ApiOperation(value = "Listado paginable de los pedidos")
    public Page<IngredientShortDTO> getAll(@ApiParam(required = false) Pageable page) {
        var content = srv.getAll(page);
        return new PageImpl(content.getContent().stream().map(item -> IngredientShortDTO.from(item)).toList(), page,
                content.getTotalElements());
    }

	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Detalles de un ingrediente")
	public IngredientDetailsDTO getOneDetails(@PathVariable int id,
			@RequestParam(required = false, defaultValue = "details") String mode) throws NotFoundException {
		return IngredientDetailsDTO.from(srv.getOne(id));
	}

	@PostMapping
	@Transactional
	@ApiOperation(value = "Añadir un nuevo ingrediente")
	@ApiResponses({ @ApiResponse(code = 201, message = "Ingrediente añadido"),
			@ApiResponse(code = 400, message = "Error al validar los datos o clave duplicada"),
			@ApiResponse(code = 404, message = "Ingrediente no encontrado") })
	public ResponseEntity<Object> create(@Valid @RequestBody IngredientEditDTO item)
			throws InvalidDataException, DuplicateKeyException, NotFoundException {
		var entity = IngredientEditDTO.from(item);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		entity = srv.add(entity);
		//item.update(entity);
		srv.change(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getIdIngredient()).toUri();
		return ResponseEntity.created(location).build();

	}

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
