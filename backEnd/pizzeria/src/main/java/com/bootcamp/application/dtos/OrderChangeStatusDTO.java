package com.bootcamp.application.dtos;

import com.bootcamp.domains.entities.Order;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class OrderChangeStatusDTO {

	@JsonProperty("idPedido")
	private int idOrder;
	@JsonProperty("direccion")
	private String address;
	
	
	public static OrderChangeStatusDTO from(Order source) {
		return new OrderChangeStatusDTO(			
				source.getIdOrder(),
				source.getAddress()
				);
	}
	
	public static Order from(OrderChangeStatusDTO source) {
		return new Order(
				source.getIdOrder(),
				source.getAddress()
				);			
	}
	
	public Order update(Order target) {
		switch(target.getStatus()) {
		case "ordered": 
			target.setStatus("in_process");
			break;
		case "in_process":
			target.setStatus("ready");
			break;
		}
		target.setAddress(address);
		return target;
	}
	
}
