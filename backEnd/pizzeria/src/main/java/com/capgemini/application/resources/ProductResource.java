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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capgemini.application.dtos.IngredientsPerPizzaDTO;
import com.capgemini.application.dtos.PizzaEditDTO;
import com.capgemini.application.dtos.ProductDetailsDTO;
import com.capgemini.application.dtos.ProductEditDTO;
import com.capgemini.application.dtos.ProductShortDTO;
import com.capgemini.domains.contracts.services.PizzaService;
import com.capgemini.domains.contracts.services.ProductService;
import com.capgemini.domains.entities.Category.Type;
import com.capgemini.exceptions.DuplicateKeyException;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.exceptions.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/product")
@Api(value = "/Product", description = "Mantenimiento de productos", produces = "application/json, application/xml", consumes = "application/json, application/xml")
public class ProductResource {
	@Autowired
	private ProductService srv;
	@Autowired
	private PizzaService srvPizza;

	@GetMapping
	@ApiOperation(value = "Listado de los productos")
	public List<ProductShortDTO> getAll() {
		return srv.getAll().stream().map(Product -> ProductShortDTO.from(Product)).toList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(params = "page")
	@ApiOperation(value = "Listado paginable de los productos")
	public Page<ProductShortDTO> getAll(@ApiParam(required = false) Pageable page) {
		var content = srv.getAll(page);
		return new PageImpl(content.getContent().stream().map(item -> ProductShortDTO.from(item)).toList(), page,
				content.getTotalElements());

	}

	@GetMapping(path = "/{id}")
	public ProductDetailsDTO getOneDetails(@PathVariable int id,
			@RequestParam(required = false, defaultValue = "details") String mode) throws NotFoundException {
		return ProductDetailsDTO.from(srv.getOne(id));
	}

	@GetMapping(path = "/{id}", params = "mode=edit")
	@ApiOperation(value = "Recupera un producto")
	@ApiResponses({ @ApiResponse(code = 200, message = "Producto encontrado"),
			@ApiResponse(code = 404, message = "Producto no encontrado") })

	public ProductEditDTO getOneEdit(@ApiParam(value = "Identificador del producto") @PathVariable int id,
			@ApiParam(value = "Versión completa o editable", required = false, allowableValues = "details,edit") @RequestParam(required = true) String mode)
			throws NotFoundException {
		return ProductEditDTO.from(srv.getOne(id));
	}

	@PostMapping
	@Transactional
	@ApiOperation(value = "Añadir un nuevo producto")
	@ApiResponses({ @ApiResponse(code = 201, message = "Producto añadido"),
			@ApiResponse(code = 400, message = "Error al validar los datos o clave duplicada"),
			@ApiResponse(code = 404, message = "Producto no encontrado") })
	public ResponseEntity<Object> create(@Valid @RequestBody ProductEditDTO item)
			throws InvalidDataException, DuplicateKeyException, NotFoundException {

		var entity = ProductEditDTO.from(item);

		if(entity.getCategory().getIdCategory() == 3) {
			var pizza = PizzaEditDTO.from(item.getPizza());
			if (pizza.isInvalid())
				throw new InvalidDataException(pizza.getErrorsMessage());
			pizza = srvPizza.add(pizza);
			for(var dto : item.getPizza().getIngredients())
				pizza.addIngredientsPerPizza(IngredientsPerPizzaDTO.from(dto, pizza));
			srvPizza.change(pizza);
			entity.setPizza(pizza);
		}
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		entity = srv.add(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getIdProduct()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@Transactional
	@ApiOperation(value = "Modificar un producto existente", notes = "Los identificadores deben coincidir")
	@ApiResponses({ @ApiResponse(code = 201, message = "Producto añadido"),
			@ApiResponse(code = 400, message = "Error al validar los datos o discrepancias en los identificadores"),
			@ApiResponse(code = 404, message = "Producto no encontrado") })
	public void update(@ApiParam(value = "Identificador del producto") @PathVariable int id,
			@Valid @RequestBody ProductEditDTO item) throws InvalidDataException, NotFoundException {
		if (id != item.getIdProduct())
			throw new InvalidDataException("No coinciden los identificadores");
		var entity = srv.getOne(id);
		item.update(entity);
		srv.change(entity);

		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		if(entity.getCategory().getIdCategory() == 3) {
			var pizza =  entity.getPizza();
			var dto = item.getPizza();
			dto.update(pizza);
			if (pizza.isInvalid())
				throw new InvalidDataException(entity.getErrorsMessage());
			srvPizza.change(pizza);
		}
	}
//
//	@DeleteMapping("/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	@ApiOperation(value = "Borrar un producto existente")
//	@ApiResponses({ @ApiResponse(code = 204, message = "Producto borrado"),
//			@ApiResponse(code = 404, message = "Producto no encontrado") })
//	public void delete(@ApiParam(value = "Identificador del producto") @PathVariable int id) {
//		srv.deleteById(id);
//	}

}
