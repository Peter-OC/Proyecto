package com.bootcamp.application.dtos;

import java.util.Date;
import java.util.List;

import com.bootcamp.domains.entities.Order;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class OrderDetailsDTO {

	@JsonProperty("idPedido")
	private int idOrder;
	@JsonProperty("idCliente")
	private String idCustomer;
	@JsonProperty("precio")
	private float price;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@JsonProperty("horaPedido")
	private Date orderDate;
	@JsonProperty("direccion")
	private String address;
	@JsonProperty("productosDelPedido")
	private List<ProductsPerOrderDTO> productosPorPedido;
	
	public static OrderDetailsDTO from(Order source) {
		return new OrderDetailsDTO(
				source.getIdOrder(),
				source.getUser(),
				source.getPrice(),
				source.getOrderDate(),
				source.getAddress(),
				source.getProductsPerOrders().stream().map(item -> ProductsPerOrderDTO.from(item)).toList()
				);
	}
}
