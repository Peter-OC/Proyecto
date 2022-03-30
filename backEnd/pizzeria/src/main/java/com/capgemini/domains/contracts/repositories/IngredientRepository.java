package com.capgemini.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.domains.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{
    <T> List<T> findByIdIngredientIsNotNull(Class<T> type);
    <T> Iterable<T> findByIdIngredientIsNotNull(Sort sort, Class<T> type);
    <T> Page<T> findByIdIngredientIsNotNull(Pageable pageable, Class<T> type);
	<T> List<T> findByType(String string, Class<T> type);
}