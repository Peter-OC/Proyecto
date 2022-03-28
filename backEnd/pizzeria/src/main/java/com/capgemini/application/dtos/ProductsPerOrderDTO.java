package com.capgemini.application.dtos;

import java.util.Date;

import com.capgemini.domains.entities.Order;
import com.capgemini.domains.entities.Product;
import com.capgemini.domains.entities.ProductsPerOrder;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
public class ProductsPerOrderDTO {

	@JsonProperty("idProducto")
	private int productId;
	@JsonProperty("nombreProducto")
	private String product;
	@JsonProperty("cantidadProducto")
	private int amount;
	
	public static ProductsPerOrderDTO from(ProductsPerOrder source) {
		return new ProductsPerOrderDTO(
				source.getProduct().getIdProduct(),
				source.getProduct().getName(),
				source.getAmount()
				);
	}
	
	public static ProductsPerOrder from(ProductsPerOrderDTO source, Order order) {
		return new ProductsPerOrder(
				source.getAmount(),
				new Product(source.getProductId()),
				order
				);
				
	}
}
