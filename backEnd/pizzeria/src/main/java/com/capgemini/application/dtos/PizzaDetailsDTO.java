package com.capgemini.application.dtos;

import java.util.List;

import com.capgemini.domains.entities.Pizza;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class PizzaDetailsDTO {

	@JsonProperty("pizza")
	private String idPizza;

	@JsonProperty("base")
	private String ingredientBase;
	
	@JsonProperty("salsa")
	private String ingredientSauce;
	
	@JsonProperty("ingredientes")
	private List<IngredientShortDTO> ingredientsPerPizzas;
	
	public static PizzaDetailsDTO from(Pizza source) {
		return new PizzaDetailsDTO(
				source.getProducts().get(0).getName(),
				source.getIngredientBase().getName(),
				source.getIngredientSauce().getName(),
				source.getIngredientsPerPizzas().stream()
				.map(item -> IngredientShortDTO.from(item.getIngredient())).toList()
				);
	}
}
