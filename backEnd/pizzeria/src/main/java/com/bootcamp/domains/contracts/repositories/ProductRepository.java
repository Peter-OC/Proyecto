package com.bootcamp.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.domains.entities.Product;
import com.bootcamp.domains.entities.Product.Type;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	<T> List<T> findByIdProductIsNotNull(Class<T> type);

	<T> Iterable<T> findByIdProductIsNotNull(Sort sort, Class<T> type);

	<T> Page<T> findByIdProductIsNotNull(Pageable pageable, Class<T> type);
	
	<T> List<T> findByType(Type category, Class<T> type);
}
