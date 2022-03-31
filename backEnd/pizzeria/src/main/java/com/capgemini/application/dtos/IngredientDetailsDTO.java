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
	
	@JsonProperty("nombre")
	private String name;
	
	@JsonProperty("tipo")
	private String type;
	
	@JsonProperty("precio")
	private float price;
	
	@JsonProperty("foto")
	private String photo;
	

	public static IngredientDetailsDTO from(Ingredient source) {
		return new IngredientDetailsDTO(
				source.getIngredientId(), 
				source.getName(), 
				source.getType().toString(),
				source.getPrice(), 
				source.getPhoto()
				);
	}
}
