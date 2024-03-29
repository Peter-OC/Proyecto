package com.bootcamp.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.domains.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	<T> List<T> findByIdOrderIsNotNull(Class<T> type);
	<T> Iterable<T> findByIdOrderIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByIdOrderIsNotNull(Pageable pageable, Class<T> type);
	
	<T> List<T> findByStatus(String estado, Class<T> type);

	
}
