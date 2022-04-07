package com.bootcamp.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.domains.entities.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer>{
    <T> List<T> findByIdPizzaIsNotNull(Class<T> type);
    <T> Iterable<T> findByIdPizzaIsNotNull(Sort sort, Class<T> type);
    <T> Page<T> findByIdPizzaIsNotNull(Pageable pageable, Class<T> type);
}