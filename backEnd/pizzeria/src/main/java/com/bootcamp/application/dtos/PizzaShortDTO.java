package com.bootcamp.application.dtos;

import com.bootcamp.domains.entities.Pizza;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class PizzaShortDTO {

	@JsonProperty("idPizza")
	private int pizzaName;

	@JsonProperty("base")
	private String ingredientBase;
	
	@JsonProperty("salsa")
	private String ingredientSauce;
	
	public static PizzaShortDTO from(Pizza source) {
		return new PizzaShortDTO(
				source.getPizzaId(),
				source.getIngredientBase().getName(),
				source.getIngredientSauce().getName()
				);
	}
}
