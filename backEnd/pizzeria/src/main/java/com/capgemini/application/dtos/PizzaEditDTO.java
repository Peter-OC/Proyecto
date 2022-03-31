package com.capgemini.application.dtos;

import java.util.List;

import com.capgemini.domains.entities.Ingredient;
import com.capgemini.domains.entities.Order;
import com.capgemini.domains.entities.Pizza;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class PizzaEditDTO {

	private int idPizza;

	@JsonProperty("base")
	private int ingredientBase;
	
	@JsonProperty("salsa")
	private int ingredientSauce;
	
	private List<IngredientsPerPizzaDTO> ingredients;
	
	public static PizzaEditDTO from(Pizza source) {
		return new PizzaEditDTO(
				source.getPizzaId(),
				source.getIngredientBase().getIngredientId(),
				source.getIngredientSauce().getIngredientId(),
				source.getIngredientsPerPizzas().stream().map(item -> IngredientsPerPizzaDTO.from(item)).toList()
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
		borrarIngredientesPorPizzaSobrantes(target);
		actualizarIngredientesPorPizzaCambiados(target);
		incorporarNuevosIngredientesPorPizza(target);
		return target;
	}

	private void actualizaPropiedadesEntidad(Pizza target) {
		target.setIngredientBase(new Ingredient(ingredientBase));
		target.setIngredientSauce(new Ingredient(ingredientSauce));
	}
	
	private void borrarIngredientesPorPizzaSobrantes(Pizza target) {
		target.getIngredientsPerPizzas().stream()
								.filter(entity -> ingredients.stream().noneMatch(dto -> entity.getId().getIdIngredient() == dto.getIngredientId())).toList()
								.forEach(item -> target.removeIngredientsPerPizza(item));
	}

	private void actualizarIngredientesPorPizzaCambiados(Pizza target) {
		target.getIngredientsPerPizzas().forEach(entity -> {
			var dto = ingredients.stream().filter(item -> item.getIngredientId() == entity.getId().getIdIngredient()).findFirst().get();
			if (entity.getAmount() != dto.getAmount())
				entity.setAmount(dto.getAmount());
		});
	}
	
	private void incorporarNuevosIngredientesPorPizza(Pizza target) {
		ingredients.stream().filter(
				dto -> target.getIngredientsPerPizzas().stream().noneMatch(entity -> entity.getId().getIdIngredient() == dto.getIngredientId()))
				.forEach(dto -> target.addIngredientsPerPizza(IngredientsPerPizzaDTO.from(dto, target)));
		
	}
	
}
