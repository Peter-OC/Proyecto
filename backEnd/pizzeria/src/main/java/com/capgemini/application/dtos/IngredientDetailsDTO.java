package com.capgemini.application.dtos;

import java.util.List;

import com.capgemini.domains.entities.Ingredient;
import com.capgemini.domains.entities.IngredientsPerPizza;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class IngredientDetailsDTO {
	
	@JsonProperty("id")
	private int ingredientId;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("price")
	private float price;
	
	@JsonProperty("photoURL")
	private String photo;
	
	@JsonProperty("Lista-pizzas")
	private List<IngredientsPerPizzaDTO> ingredientsPerPizzas;
	

	public static IngredientDetailsDTO from(Ingredient source) {
		return new IngredientDetailsDTO(
				source.getIdIngredient(), 
				source.getName(), 
				source.getType().toString(),
				source.getPrice(), 
				source.getPhoto(),
				source.getIngredientsPerPizzas().stream().map(item -> IngredientsPerPizzaDTO.from(item)).toList()
				);
	}
}
