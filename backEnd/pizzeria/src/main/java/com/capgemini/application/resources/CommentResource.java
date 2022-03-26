package com.capgemini.application.resources;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.application.dtos.CommentDetailsDTO;
import com.capgemini.application.dtos.CommentShortDTO;
import com.capgemini.domains.contracts.services.CommentService;
import com.capgemini.exceptions.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/comment")
@Api(value = "/Comment", description = "Mantenimiento de comentarios", produces = "application/json, application/xml", consumes = "application/json, application/xml")
public class CommentResource {
	@Autowired
	private CommentService srv;

	@GetMapping
	@ApiOperation(value = "Listado de los comentarios")
	public List<CommentShortDTO> getAll() {
		return srv.getAll().stream().map(Comment -> CommentShortDTO.from(Comment)).toList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(params = "page")
	@ApiOperation(value = "Listado paginable de los comentarios")
	public Page<CommentShortDTO> getAll(@ApiParam(required = false) Pageable page) {
		var content = srv.getAll(page);
		return new PageImpl(content.getContent().stream().map(item -> CommentShortDTO.from(item)).toList(), page,
				content.getTotalElements());

	}

	@GetMapping(path = "/{id}")
	public CommentDetailsDTO getOneDetails(@PathVariable int id,
			@RequestParam(required = false, defaultValue = "details") String mode) throws NotFoundException {
		return CommentDetailsDTO.from(srv.getOne(id));
	}

//	@GetMapping(path = "/{id}", params = "mode=edit")
//	@ApiOperation(value = "Recupera un alquiler")
//	@ApiResponses({ @ApiResponse(code = 200, message = "Alquiler encontrado"),
//			@ApiResponse(code = 404, message = "Alquiler no encontrado") })
//
//	public CommentEditDTO getOneEdit(@ApiParam(value = "Identificador del alquiler") @PathVariable int id,
//			@ApiParam(value = "Versión completa o editable", required = false, allowableValues = "details,edit") @RequestParam(required = true) String mode)
//			throws NotFoundException {
//		return CommentEditDTO.from(srv.getOne(id));
//	}

//	@PostMapping
//	@Transactional
//	@ApiOperation(value = "Añadir un nuevo alquiler")
//	@ApiResponses({ @ApiResponse(code = 201, message = "Aluiler añadido"),
//			@ApiResponse(code = 400, message = "Error al validar los datos o clave duplicada"),
//			@ApiResponse(code = 404, message = "Alquiler no encontrado") })
//	public ResponseEntity<Object> create(@Valid @RequestBody CommentEditDTO item)
//			throws InvalidDataException, DuplicateKeyException, NotFoundException {
//		var entity = CommentEditDTO.from(item);
//		if (entity.isInvalid())
//			throw new InvalidDataException(entity.getErrorsMessage());
//		entity = srv.add(entity);
//		item.update(entity);
//		srv.change(entity);
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(entity.getCommentId()).toUri();
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
//			@Valid @RequestBody CommentEditDTO item) throws InvalidDataException, NotFoundException {
//		if (id != item.getCommentId())
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
