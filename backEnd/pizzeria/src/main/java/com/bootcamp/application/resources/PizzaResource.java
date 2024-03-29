package com.bootcamp.application.resources;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bootcamp.application.dtos.PizzaDetailsDTO;
import com.bootcamp.application.dtos.PizzaEditDTO;
import com.bootcamp.application.dtos.PizzaShortDTO;
import com.bootcamp.domains.contracts.services.PizzaService;
import com.bootcamp.exceptions.DuplicateKeyException;
import com.bootcamp.exceptions.InvalidDataException;
import com.bootcamp.exceptions.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/pizza")
@Api(value = "/Pizza", description = "Mantenimiento de Pizzas", produces = "application/json, application/xml", consumes = "application/json, application/xml")
public class PizzaResource {
	@Autowired
	private PizzaService srv;

	@GetMapping
	@ApiOperation(value = "Listado de las Pizzas")
	public List<PizzaShortDTO> getAll() {
		return srv.getAll().stream().map(item -> PizzaShortDTO.from(item)).toList();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    @GetMapping(params = "page")
    @ApiOperation(value = "Listado paginable de las Pizzas")
    public Page<PizzaShortDTO> getAll(@ApiParam(required = false) Pageable page) {
        var content = srv.getAll(page);
        return new PageImpl(content.getContent().stream().map(item -> PizzaShortDTO.from(item)).toList(), page,
                content.getTotalElements());
    }

	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Detalles de una Pizza")
	public PizzaDetailsDTO getOneDetails(@PathVariable int id,
			@RequestParam(required = false, defaultValue = "details") String mode) throws NotFoundException {
		return PizzaDetailsDTO.from(srv.getOne(id));
	}
	@GetMapping(path = "/{id}", params = { "mode=edit" })
	@ApiOperation(value = "Detalles de una Pizza")
	public PizzaEditDTO getOneEdit(@PathVariable int id,
			@RequestParam(required = false, defaultValue = "edit") String mode) throws NotFoundException {
		return PizzaEditDTO.from(srv.getOne(id));
	}

	@PostMapping
	@Transactional
	@ApiOperation(value = "Añadir una nueva Pizza")
	@ApiResponses({ @ApiResponse(code = 201, message = "Pizza añadida"),
			@ApiResponse(code = 400, message = "Error al validar los datos o clave duplicada"),
			@ApiResponse(code = 404, message = "Pizza no encontrada") })
	public ResponseEntity<Object> create(@Valid @RequestBody PizzaEditDTO item) throws InvalidDataException, DuplicateKeyException, NotFoundException {
		var entity = PizzaEditDTO.from(item);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		entity = srv.add(entity);
		item.update(entity);
		srv.change(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getPizzaId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@Transactional
	@ApiOperation(value = "Modificar una Pizza existente", notes = "Los identificadores deben coincidir")
	@ApiResponses({ @ApiResponse(code = 201, message = "Pizza añadida"),
			@ApiResponse(code = 400, message = "Error al validar los datos o discrepancias en los identificadores"),
			@ApiResponse(code = 404, message = "Pizza no encontrada") })
	public void update(@ApiParam(value = "Identificador de la Pizza") @PathVariable int id,
			@Valid @RequestBody PizzaEditDTO item) throws InvalidDataException, NotFoundException {
		if (id != item.getIdPizza())
			throw new InvalidDataException("No coinciden los identificadores");
		var entity = srv.getOne(id);
		item.update(entity);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		srv.change(entity);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Borrar una Pizza existente")
	@ApiResponses({ @ApiResponse(code = 204, message = "Pizza borrada"),
			@ApiResponse(code = 404, message = "Pizza no encontrada") })
	public void delete(@ApiParam(value = "Identificador de la pizza") @PathVariable int id) {
		srv.deleteById(id);
	}

}
