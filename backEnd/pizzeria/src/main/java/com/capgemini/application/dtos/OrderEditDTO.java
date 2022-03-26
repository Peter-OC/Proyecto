package com.capgemini.application.dtos;

import java.util.Date;
import java.util.List;

import com.capgemini.domains.entities.Order;
import com.capgemini.domains.entities.Product;
import com.capgemini.domains.entities.ProductsPerOrder;
import com.capgemini.domains.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
public class OrderEditDTO {

	@JsonProperty("idPedido")
	private int idOrder;
	@JsonProperty("idUsuario")
	private int idUser;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@JsonProperty("horaPedido")
	private Date orderDate;
	@JsonProperty("direccion")
	private String address;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@JsonProperty("horaLlegada")
	private Date orderDelivery;
	@JsonProperty("precioPedido")
	private float precio;
	@JsonProperty("estadoPedido")
	private String estado;
	@JsonProperty("productosDelPedido")
	private List<ProductsPerOrderDTO> productosPorPedido;
	
	public static OrderEditDTO from(Order source) {
		return new OrderEditDTO(			
				source.getIdOrder(),
				source.getUser().getIdUser(),
				source.getOrderDate(),
				source.getAddress(),
				source.getDeliveryDate(),
				source.getPrice(),
				source.getStatus() == null ? null : source.getStatus().getValue(),
				source.getProductsPerOrders().stream().map(item -> ProductsPerOrderDTO.from(item)).toList()
				);
	}
	
	public static Order from(OrderEditDTO source) {
		return new Order(
				source.getIdOrder(),
				new User(source.getIdUser()),
				source.getOrderDate(),
				source.getAddress(),
				source.getOrderDelivery(),
				source.getPrecio(),
				source.getEstado() == null ? null : Order.Status.getEnum(source.getEstado())			
				);			
	}
	
	public Order update(Order target) {
		actualizaPropiedadesEntidad(target);
		borrarProductosPorPedidoSobrantes(target);
		actualizarProductosPorPedidoCambiados(target);
		incorporarNuevosProductosPorPedido(target);
		return target;
	}
	
	private void actualizaPropiedadesEntidad(Order target) {
		if (target.getUser().getIdUser() != idUser)
			target.setUser(new User(idUser));
		target.setOrderDate(orderDate);
		target.setAddress(address);
		target.setDeliveryDate(orderDelivery);
		target.setPrice(precio);
		target.setStatus(estado == null ? null : Order.Status.getEnum(estado));	
		}
	
	private void borrarProductosPorPedidoSobrantes(Order target) {
		target.getProductsPerOrders().stream()
								.filter(entity -> productosPorPedido.stream().noneMatch(dto -> entity.getId().getIdProduct() == dto.getProductId())).toList()
								.forEach(item -> target.removeProductsPerOrder(item));
	}

	private void actualizarProductosPorPedidoCambiados(Order target) {
		target.getProductsPerOrders().forEach(entity -> {
			var dto = productosPorPedido.stream().filter(item -> item.getProductId() == entity.getId().getIdProduct()).findFirst().get();
			if (entity.getAmount() != dto.getAmount())
				entity.setAmount(dto.getAmount());
		});
	}
	
	private void incorporarNuevosProductosPorPedido(Order target) {
		productosPorPedido.stream().filter(
				dto -> target.getProductsPerOrders().stream().noneMatch(entity -> entity.getId().getIdProduct() == dto.getProductId()))
				.forEach(dto -> target.addProductsPerOrder(ProductsPerOrderDTO.from(dto, target)));
		
	}
	
}
