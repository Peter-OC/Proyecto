package com.bootcamp.domains.contracts.services;

import java.util.List;

import com.bootcamp.exceptions.DuplicateKeyException;
import com.bootcamp.exceptions.InvalidDataException;
import com.bootcamp.exceptions.NotFoundException;

public interface DomainService<E, K> {
	List<E> getAll();

	E getOne(K id) throws NotFoundException;

	E add(E item) throws DuplicateKeyException, InvalidDataException;

	E change(E item) throws NotFoundException, InvalidDataException;

	void delete(E item);

	void deleteById(K id);
}