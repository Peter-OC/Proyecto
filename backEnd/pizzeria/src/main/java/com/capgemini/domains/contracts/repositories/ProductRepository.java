package com.capgemini.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.domains.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	<T> List<T> findByIdProductIsNotNull(Class<T> type);

	<T> Iterable<T> findByIdProductIsNotNull(Sort sort, Class<T> type);

	<T> Page<T> findByIdProductIsNotNull(Pageable pageable, Class<T> type);
}
