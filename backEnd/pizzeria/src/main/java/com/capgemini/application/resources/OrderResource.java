package com.capgemini.application.resources;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.http.HttpStatus;

import com.capgemini.application.dtos.OrderChangeStatusDTO;
import com.capgemini.application.dtos.OrderDetailsDTO;
import com.capgemini.application.dtos.OrderEditDTO;
import com.capgemini.application.dtos.OrderShortDTO;
import com.capgemini.domains.contracts.services.OrderService;
import com.capgemini.exceptions.DuplicateKeyException;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.exceptions.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/order")
@Api(value = "/Order", description = "Mantenimiento de pedidos", produces = "application/json, application/xml",
	 consumes = "application/json, application/xml")
public class OrderResource {
	@Autowired
	private OrderService srv;

	@GetMapping
	@ApiOperation(value = "Listado de todos los pedidos")
	public List<OrderShortDTO> getAll() {
		return srv.getByProjection(OrderShortDTO.class);
	}

	@GetMapping(params = "page")
	@ApiOperation(value = "Listado paginable de los pedidos")
	public Page<OrderShortDTO> getAll(@ApiParam(required = false) Pageable page) {
		return srv.getByProjection(page, OrderShortDTO.class);

	}

	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Listado detallado de los pedidos")
	public OrderDetailsDTO getOneDetails(@PathVariable int id,
			@RequestParam(required = false, defaultValue = "details") String mode) throws NotFoundException {
		return OrderDetailsDTO.from(srv.getOne(id));
	}
	
	@GetMapping(path = "/ordered")
	@ApiOperation(value = "Listado Pedidos por estado ordered")
	public List<OrderShortDTO> getOrdereds(@RequestParam(required=false, defaultValue = "details")String mode)
	throws NotFoundException {
		return srv.getOrdered(OrderShortDTO.class);
	}
	
	@GetMapping(path = "/inProcess")
	@ApiOperation(value = "Listado Pedidos por estado en proceso")
	public List<OrderShortDTO> getInProcess(@RequestParam(required=false, defaultValue = "details")String mode)
	throws NotFoundException {
		return srv.getInProcess(OrderShortDTO.class);
	}
	
	
	@GetMapping(path = "/readies")
	@ApiOperation(value = "Listado Pedidos por estado listo")
	public List<OrderShortDTO> getOneReadies(@RequestParam(required=false, defaultValue = "details")String mode)
	throws NotFoundException {
		return srv.getReady(OrderShortDTO.class);
	}
	
	@GetMapping(path = "/sents")
	@ApiOperation(value = "Listado Pedidos por estado enviado")
	public List<OrderShortDTO> getOneSents(@RequestParam(required=false, defaultValue = "details") String mode)
	throws NotFoundException {
		return srv.getSent(OrderShortDTO.class);
	}
	
	@GetMapping(path = "/receiveds")
	@ApiOperation(value = "Listado Pedidos por estado recibido")
	public List<OrderShortDTO> getOneReceiveds(@RequestParam(required = false, defaultValue = "details") String mode)
	throws NotFoundException {
		return srv.getReceived(OrderShortDTO.class);
	}
	
	@GetMapping(path = "/canceleds")
	@ApiOperation(value = "Listado Pedidos por estado cancelado")
	public List<OrderShortDTO> getOneDetails(@RequestParam(required = false, defaultValue = "details") String mode)
	throws NotFoundException {
		return srv.getCanceled(OrderShortDTO.class);
	}
	
	
	@GetMapping(path = "/{id}", params = "mode=edit")
	@ApiOperation(value = "Recupera un pedido")
	@ApiResponses({ @ApiResponse(code = 200, message = "Pedido encontrado"),
			@ApiResponse(code = 404, message = "Pedido no encontrado") })

	public OrderEditDTO getOneEdit(@ApiParam(value = "Identificador del pedido") @PathVariable int id,
			@ApiParam(value = "Versión completa o editable", required = false, allowableValues = "details,edit") 
			@RequestParam(required = true) String mode)
			throws NotFoundException {
		return OrderEditDTO.from(srv.getOne(id));
	}

	@PostMapping
	@Transactional
	@ApiOperation(value = "Añadir un nuevo pedido")
	@ApiResponses({ @ApiResponse(code = 201, message = "Pedido añadido"),
			@ApiResponse(code = 400, message = "Error al validar los datos o clave duplicada"),
			@ApiResponse(code = 404, message = "Pedido no encontrado") })
	public ResponseEntity<Object> create(@Valid @RequestBody OrderEditDTO item)
			throws InvalidDataException, DuplicateKeyException, NotFoundException {
		var entity = OrderEditDTO.from(item);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		entity = srv.add(entity);
		item.update(entity);
		srv.change(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getIdOrder()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@Transactional
	@ApiOperation(value = "Modificar un pedido existente", notes = "Los identificadores deben coincidir")
	@ApiResponses({ @ApiResponse(code = 201, message = "Pedido actualizado"),
			@ApiResponse(code = 400, message = "Error al validar los datos o discrepancias en los identificadores"),
			@ApiResponse(code = 404, message = "Pedio no encontrado") })
	public void update(@ApiParam(value = "Identificador del pedido") @PathVariable int id,
			@Valid @RequestBody OrderEditDTO item) throws InvalidDataException, NotFoundException {
		if (id != item.getIdOrder())
			throw new InvalidDataException("No coinciden los identificadores");
		var entity = srv.getOne(id);
		item.update(entity);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		srv.change(entity);
	}
	
	@PutMapping("/change/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@Transactional
	@ApiOperation(value = "Modificar el estado de un pedido", notes = "Los identificadores deben coincidir")
	@ApiResponses({ @ApiResponse(code = 201, message = "Estado del pedido actualizado"),
			@ApiResponse(code = 400, message = "Error al validar los datos o discrepancias en los identificadores"),
			@ApiResponse(code = 404, message = "Pedio no encontrado") })
	public void change(@ApiParam(value = "Identificador del pedido") @PathVariable int id,
			@Valid @RequestBody OrderChangeStatusDTO item) throws InvalidDataException, NotFoundException {
		if (id != item.getIdOrder())
			throw new InvalidDataException("No coinciden los identificadores");
		var entity = srv.getOne(id);
		item.update(entity);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		srv.change(entity);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Borrar un pedido existente")
	@ApiResponses({ @ApiResponse(code = 204, message = "Pedido borrado"),
			@ApiResponse(code = 404, message = "Pedido no encontrado") })
	public void delete(@ApiParam(value = "Identificador del pedido") @PathVariable int id) {
		srv.deleteById(id);
	}

}
