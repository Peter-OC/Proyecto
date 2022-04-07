package com.bootcamp.application.dtos;

import com.bootcamp.domains.entities.Ingredient;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class IngredientShortDTO {

	@JsonProperty("id")
	private int idIngredient;

	@JsonProperty("nombre")
	private String name;
	
	public static IngredientShortDTO from(Ingredient source) {
		return new IngredientShortDTO(
				source.getIngredientId(),
				source.getName()
				);
	}
}
