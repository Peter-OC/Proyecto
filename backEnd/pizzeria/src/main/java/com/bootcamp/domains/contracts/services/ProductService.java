package com.bootcamp.domains.contracts.services;

import java.util.List;

import com.bootcamp.domains.entities.Product;

public interface ProductService extends ProjectionDomainService<Product, Integer> {

	<T> List<T> getPizzas(Class<T> type);
	<T> List<T> getEntrantes(Class<T> type);
	<T> List<T> getBebidas(Class<T> type);
	
}
