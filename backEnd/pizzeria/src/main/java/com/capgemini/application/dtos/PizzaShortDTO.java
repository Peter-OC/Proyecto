package com.capgemini.application.dtos;

import com.capgemini.domains.entities.Pizza;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class PizzaShortDTO {

	@JsonProperty("pizza")
	private String pizzaName;

	@JsonProperty("base")
	private String ingredientBase;
	
	@JsonProperty("salsa")
	private String ingredientSauce;
	
	public static PizzaShortDTO from(Pizza source) {
		return new PizzaShortDTO(
				source.getProducts().get(0).getName(),
				source.getIngredientBase().getName(),
				source.getIngredientSauce().getName()
				);
	}
}
