package com.capgemini.application.dtos;

import java.util.Date;

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
	@JsonProperty("idCliente")
	private String idCustomer;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@JsonProperty("horaPedido")
	private Date orderDate;
	@JsonProperty("direccion")
	private String address;
	
	public static OrderShortDTO from(Order source) {
		return new OrderShortDTO(
				source.getIdOrder(),
				source.getUser(),
				source.getOrderDate(),
				source.getAddress()
				);
	}
}
