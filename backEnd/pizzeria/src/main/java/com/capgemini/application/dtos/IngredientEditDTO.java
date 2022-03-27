package com.capgemini.application.dtos;

import com.capgemini.domains.entities.Ingredient;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class IngredientEditDTO {


	private int ingredientId;
	private String name;
	@ApiModelProperty(value = "Tipos de Ingredientes.", allowableValues = "sauce,base,other")
	private String type;
	private float price;
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
		target.setIdIngredient(ingredientId);
		target.setName(name);
		target.setType(type);
		target.setPrice(price);
		target.setPhoto(photo);
	}

}
