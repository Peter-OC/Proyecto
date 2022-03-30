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

import com.capgemini.application.dtos.CategoryEditDTO;
import com.capgemini.application.dtos.CategoryShortDTO;
import com.capgemini.application.dtos.CategoryEditDTO;
import com.capgemini.domains.contracts.services.CategoryService;
import com.capgemini.domains.entities.Category;
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
@RequestMapping("/api/category")
@Api(value = "/Category", description = "Mantenimiento de categorías", produces = "application/json, application/xml", consumes = "application/json, application/xml")
public class CategoryResource {
	@Autowired
	private CategoryService srv;

	@GetMapping
	@ApiOperation(value = "Listado de los categorías")
	public List<CategoryShortDTO> getAll() {
		return srv.getAll().stream().map(Category -> CategoryShortDTO.from(Category)).toList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(params = "page")
	@ApiOperation(value = "Listado paginable de los categorías")
	public Page<CategoryShortDTO> getAll(@ApiParam(required = false) Pageable page) {
		var content = srv.getAll(page);
		return new PageImpl(content.getContent().stream().map(item -> CategoryShortDTO.from(item)).toList(), page,
				content.getTotalElements());

	}

	@GetMapping(path = "/{id}")
	public CategoryShortDTO getOneDetails(@PathVariable int id,
			@RequestParam(required = false, defaultValue = "details") String mode) throws NotFoundException {
		return CategoryShortDTO.from(srv.getOne(id));
	}

	@GetMapping(path = "/{id}", params = "mode=edit")
	@ApiOperation(value = "Recupera una categoría")
	@ApiResponses({ @ApiResponse(code = 200, message = "Categoría encontrada"),
			@ApiResponse(code = 404, message = "Categoría no encontrada") })

	public CategoryEditDTO getOneEdit(@ApiParam(value = "Identificador de la categoría") @PathVariable int id,
			@ApiParam(value = "Versión completa o editable", required = false, allowableValues = "details,edit") @RequestParam(required = true) String mode)
			throws NotFoundException {
		return CategoryEditDTO.from(srv.getOne(id));
	}
	
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@Transactional
	@ApiOperation(value = "Modificar una categoría existente", notes = "Los identificadores deben coincidir")
	@ApiResponses({ @ApiResponse(code = 201, message = "Categoría añadida"),
			@ApiResponse(code = 400, message = "Error al validar los datos o discrepancias en los identificadores"),
			@ApiResponse(code = 404, message = "Categoría no encontrada") })
	public void update(@ApiParam(value = "Identificador de la categoría") @PathVariable int id,
			@Valid @RequestBody CategoryEditDTO item) throws InvalidDataException, NotFoundException {
		if (id != item.getIdCategory())
			throw new InvalidDataException("No coinciden los identificadores");
		var entity = srv.getOne(id);
		item.update(entity);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		srv.change(entity);
	}

}
