package com.capgemini.application.dtos;

import com.capgemini.domains.entities.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductShortDTO {

	@JsonProperty("id")
	private int idProduct;

	@JsonProperty("nombre")
	private String name;


	@JsonProperty("precio")
	private float price;


	
	public static ProductShortDTO from(Product source) {
		return new ProductShortDTO(
				source.getIdProduct(),
				source.getName(),
				source.getPrice()
				);
	}
}
