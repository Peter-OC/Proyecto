package com.capgemini.application.dtos;

import java.util.Date;
import java.util.List;

import com.capgemini.domains.entities.Order;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class OrderShortDTO {

	@JsonProperty("idPedido")
	private int idOrder;
	@JsonProperty("estado")
	private String estado;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@JsonProperty("horaPedido")
	private Date orderDate;
	@JsonProperty("productosDelPedido")
	private List<ProductsPerOrderDTO> productos;
	
	
	public static OrderShortDTO from(Order source) {
		return new OrderShortDTO(
				source.getIdOrder(),
				source.getStatus(),
				source.getOrderDate(),
				source.getProductsPerOrders().stream().map(item -> ProductsPerOrderDTO.from(item)).toList()
				);
	}
}
