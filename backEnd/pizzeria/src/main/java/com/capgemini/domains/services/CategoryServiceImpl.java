package com.capgemini.domains.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capgemini.domains.contracts.repositories.CategoryRepository;
import com.capgemini.domains.contracts.services.CategoryService;
import com.capgemini.domains.entities.Category;
import com.capgemini.exceptions.DuplicateKeyException;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.exceptions.NotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryRepository dao;

	public CategoryServiceImpl(CategoryRepository dao) {
		this.dao = dao;
	}

	@Override
	public List<Category> getAll() {
		return dao.findAll();
	}

	@Override
	public Iterable<Category> getAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Category> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByIdCategoryIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByIdCategoryIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByIdCategoryIsNotNull(pageable, type);
	}

	@Override
	public Category getOne(Integer id) throws NotFoundException {
		var item = dao.findById(id);
		if (item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}

	@Override
	public Category add(Category item) throws DuplicateKeyException, InvalidDataException {
		if (item == null)
			throw new IllegalArgumentException();
		if (dao.findById(item.getIdCategory()).isPresent())
			throw new DuplicateKeyException();
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public Category change(Category item) throws NotFoundException, InvalidDataException {
		if (item == null)
			throw new IllegalArgumentException();
		if (dao.findById(item.getIdCategory()).isEmpty())
			throw new NotFoundException();
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public void delete(Category item) {
		if (item == null)
			throw new IllegalArgumentException();
		deleteById(item.getIdCategory());

	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
}
