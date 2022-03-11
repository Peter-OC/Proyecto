package com.capgemini.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.domains.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
	<T> List<T> findByIngredientIdIsNotNull(Class<T> type);

	<T> Iterable<T> findByIngredientIdIsNotNull(Sort sort, Class<T> type);

	<T> Page<T> findByIngredientIdIsNotNull(Pageable pageable, Class<T> type);
}
