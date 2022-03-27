package com.capgemini.application.dtos;

import com.capgemini.domains.entities.Ingredient;
import com.capgemini.domains.entities.Pizza;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class PizzaEditDTO {

	@JsonProperty("pizza")
	private int idPizza;

	@JsonProperty("base")
	private int ingredientBase;
	
	@JsonProperty("salsa")
	private int ingredientSauce;
	
	public static PizzaEditDTO from(Pizza source) {
		return new PizzaEditDTO(
				source.getPizzaId(),
				source.getIngredientBase().getIngredientId(),
				source.getIngredientSauce().getIngredientId()
				);
	}
	
	public static Pizza from(PizzaEditDTO source) {
		return new Pizza(
				source.getIdPizza(),
				new Ingredient(source.ingredientBase),
				new Ingredient(source.ingredientSauce)
				);
	}
	
	public Pizza update(Pizza target) {
		actualizaPropiedadesEntidad(target);
		return target;
	}

	private void actualizaPropiedadesEntidad(Pizza target) {
		target.setIdPizza(idPizza);
		target.setIngredientBase(new Ingredient(ingredientBase));
		target.setIngredientSauce(new Ingredient(ingredientSauce));
	}

}
