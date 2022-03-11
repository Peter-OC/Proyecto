package com.capgemini.application.dtos;

import com.capgemini.domains.entities.Ingredient;
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
				source.getIdIngredient(),
				source.getName()
				);
	}
}
