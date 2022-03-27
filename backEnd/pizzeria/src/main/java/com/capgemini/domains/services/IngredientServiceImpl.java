package com.capgemini.domains.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capgemini.domains.contracts.repositories.IngredientRepository;
import com.capgemini.domains.contracts.services.IngredientService;
import com.capgemini.domains.entities.Ingredient;
import com.capgemini.exceptions.DuplicateKeyException;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.exceptions.NotFoundException;

@Service
public class IngredientServiceImpl implements IngredientService {
	private IngredientRepository dao;

	public IngredientServiceImpl(IngredientRepository dao) {
		this.dao = dao;
	}

	@Override
	public List<Ingredient> getAll() {
		return dao.findAll();
	}

	@Override
	public Iterable<Ingredient> getAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Ingredient> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByIdIngredientIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByIdIngredientIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByIdIngredientIsNotNull(pageable, type);
	}

	@Override
	public Ingredient getOne(Integer id) throws NotFoundException {
		var item = dao.findById(id);
		if (item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}

	@Override
	public Ingredient add(Ingredient item) throws DuplicateKeyException, InvalidDataException {
		if (item == null)
			throw new IllegalArgumentException();
		if (dao.findById(item.getIngredientId()).isPresent())
			throw new DuplicateKeyException();
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public Ingredient change(Ingredient item) throws NotFoundException, InvalidDataException {
		if (item == null)
			throw new IllegalArgumentException();
		if (dao.findById(item.getIngredientId()).isEmpty())
			throw new NotFoundException();
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public void delete(Ingredient item) {
		if (item == null)
			throw new IllegalArgumentException();
		deleteById(item.getIngredientId());

	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
	
	@Override
	public <T> List<T> getSalsas(Class<T> type) {
	// TODO Auto-generated method stub
	return dao.findByType("sauce", type);
	}

	@Override
	public <T> List<T> getBases(Class<T> type) {
	// TODO Auto-generated method stub
	return dao.findByType("base", type);
	}

	@Override
	public <T> List<T> getOtros(Class<T> type) {
	// TODO Auto-generated method stub
	return dao.findByType("other", type);
	}
}
