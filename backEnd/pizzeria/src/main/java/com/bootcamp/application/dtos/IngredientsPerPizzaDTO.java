package com.bootcamp.application.dtos;

import com.bootcamp.domains.entities.Ingredient;
import com.bootcamp.domains.entities.IngredientsPerPizza;
import com.bootcamp.domains.entities.Pizza;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value @AllArgsConstructor
public class IngredientsPerPizzaDTO {

	@JsonProperty("id_ingrediente")
	private int ingredientId;
	
	@JsonProperty("cantidad")
	private int amount;
	

	public static IngredientsPerPizzaDTO from(IngredientsPerPizza source) {
		return new IngredientsPerPizzaDTO(
				source.getIngredient().getIngredientId(),
				source.getAmount()
				);
	}
	
	public static IngredientsPerPizza from(IngredientsPerPizzaDTO source, Pizza pizza) {
		return new IngredientsPerPizza(
				source.getAmount(),
				new Ingredient(source.ingredientId),
				pizza
				);
		}
}
