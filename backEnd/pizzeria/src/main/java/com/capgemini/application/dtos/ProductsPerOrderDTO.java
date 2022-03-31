package com.capgemini.application.dtos;

import com.capgemini.domains.entities.Order;
import com.capgemini.domains.entities.Product;
import com.capgemini.domains.entities.ProductsPerOrder;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class ProductsPerOrderDTO {

	@JsonProperty("idProducto")
	private int productId;
	@JsonProperty("nombreProducto")
	private String product;
	@JsonProperty("cantidadProducto")
	private int amount;
	@JsonProperty("precio")
	private float precio;
	
	public static ProductsPerOrderDTO from(ProductsPerOrder source) {
		return new ProductsPerOrderDTO(
				source.getProduct().getIdProduct(),
				source.getProduct().getName(),
				source.getAmount(),
				source.getPrice()
				);
	}
	
	public static ProductsPerOrder from(ProductsPerOrderDTO source, Order order) {
		return new ProductsPerOrder(
				source.getAmount(),
				source.getPrecio(),
				new Product(source.getProductId()),
				order
				);
				
	}
}
