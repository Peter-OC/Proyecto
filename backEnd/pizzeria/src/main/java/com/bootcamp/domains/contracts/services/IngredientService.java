package com.bootcamp.domains.contracts.services;

import java.util.List;

import com.bootcamp.domains.entities.Ingredient;

public interface IngredientService extends ProjectionDomainService<Ingredient, Integer> {

	<T> List<T> getSalsas(Class<T> type);
	<T> List<T> getBases(Class<T> type);
	<T> List<T> getOtros(Class<T> type);

}
