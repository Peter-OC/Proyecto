package com.bootcamp.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.domains.entities.ProductsPerOrder;
import com.bootcamp.domains.entities.ProductsPerOrderPK;

public interface ProductsPerOrderRepository extends JpaRepository<ProductsPerOrder, ProductsPerOrderPK>{
	<T> List<T> findByIdIsNotNull(Class<T> type);
	<T> Iterable<T> findByIdIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByIdIsNotNull(Pageable pageable, Class<T> type);
}
