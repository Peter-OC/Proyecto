package com.capgemini.domains.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capgemini.domains.contracts.repositories.ProductsPerOrderRepository;
import com.capgemini.domains.contracts.services.ProductsPerOrderService;
import com.capgemini.domains.entities.ProductsPerOrder;
import com.capgemini.domains.entities.ProductsPerOrderPK;
import com.capgemini.exceptions.DuplicateKeyException;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.exceptions.NotFoundException;

@Service
public class ProductsPerOrderServiceImpl implements ProductsPerOrderService {
	private ProductsPerOrderRepository dao;

	public ProductsPerOrderServiceImpl(ProductsPerOrderRepository dao) {
		this.dao = dao;
	}

	@Override
	public List<ProductsPerOrder> getAll() {
		return dao.findAll();
	}

	@Override
	public Iterable<ProductsPerOrder> getAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<ProductsPerOrder> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByIdIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByIdIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByIdIsNotNull(pageable, type);
	}

	@Override
	public ProductsPerOrder getOne(ProductsPerOrderPK id) throws NotFoundException {
		var item = dao.findById(id);
		if (item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}

	@Override
	public ProductsPerOrder add(ProductsPerOrder item) throws DuplicateKeyException, InvalidDataException {
		if (item == null)
			throw new IllegalArgumentException();
		if (dao.findById(item.getId()).isPresent())
			throw new DuplicateKeyException();
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public ProductsPerOrder change(ProductsPerOrder item) throws NotFoundException, InvalidDataException {
		if (item == null)
			throw new IllegalArgumentException();
		if (dao.findById(item.getId()).isEmpty())
			throw new NotFoundException();
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public void delete(ProductsPerOrder item) {
		if (item == null)
			throw new IllegalArgumentException();
		deleteById(item.getId());

	}

	@Override
	public void deleteById(ProductsPerOrderPK id) {
		dao.deleteById(id);
	}
}
