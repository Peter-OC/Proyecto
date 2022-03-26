package com.capgemini.application.dtos;

import com.capgemini.domains.entities.IngredientsPerPizza;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value @AllArgsConstructor
public class IngredientsPerPizzaDTO {

	@JsonProperty("cantidad")
	private int amount;
	
	@JsonProperty("Id_pizza")
	private int pizza;
	
	
	public static IngredientsPerPizzaDTO from(IngredientsPerPizza source) {
		return new IngredientsPerPizzaDTO(
				source.getAmount(),
				source.getPizza().getIdPizza()
				);
	}
}
