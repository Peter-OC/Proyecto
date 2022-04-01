package com.capgemini.application.dtos;

import com.capgemini.domains.entities.Ingredient;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class IngredientEditDTO {


	@JsonProperty("id")
	private int ingredientId;
	
	@JsonProperty("ingrediente")
	private String name;
	
	@JsonProperty("tipo")
	@ApiModelProperty(value = "Tipos de Ingredientes.", allowableValues = "sauce,base,other")
	private String type;
	
	@JsonProperty("precio")
	private float price;
	
	@JsonProperty("foto")
	private String photo;

	
	public static IngredientEditDTO from(Ingredient source) {
		return new IngredientEditDTO(
				source.getIngredientId(),
				source.getName(),
				source.getType(),
				source.getPrice(),
				source.getPhoto()
				);
	}
	
	public static Ingredient from(IngredientEditDTO source) {
		return new Ingredient(
				source.getIngredientId(),
				source.getName(),
				source.getType(),
				source.getPrice(),
				source.getPhoto()
				);
	}
	
	public Ingredient update(Ingredient target) {
		actualizaPropiedadesEntidad(target);
		return target;
	}

	private void actualizaPropiedadesEntidad(Ingredient target) {
		target.setName(name);
		target.setType(type);
		target.setPrice(price);
		target.setPhoto(photo);
	}

}
