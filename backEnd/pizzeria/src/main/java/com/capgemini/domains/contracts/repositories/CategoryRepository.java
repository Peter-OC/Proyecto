package com.capgemini.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.domains.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	<T> List<T> findByIdCategoryIsNotNull(Class<T> type);

	<T> Iterable<T> findByIdCategoryIsNotNull(Sort sort, Class<T> type);

	<T> Page<T> findByIdCategoryIsNotNull(Pageable pageable, Class<T> type);
}
