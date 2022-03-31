package com.capgemini.domains.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capgemini.domains.contracts.repositories.ProductRepository;
import com.capgemini.domains.contracts.services.ProductService;
import com.capgemini.domains.entities.Product;
import com.capgemini.domains.entities.Product.Type;
import com.capgemini.exceptions.DuplicateKeyException;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.exceptions.NotFoundException;

@Service
public class ProductServiceImpl implements ProductService {
	private ProductRepository dao;

	public ProductServiceImpl(ProductRepository dao) {
		this.dao = dao;
	}

	@Override
	public List<Product> getAll() {
		return dao.findAll();
	}

	@Override
	public Iterable<Product> getAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Product> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByIdProductIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByIdProductIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByIdProductIsNotNull(pageable, type);
	}

	@Override
	public Product getOne(Integer id) throws NotFoundException {
		var item = dao.findById(id);
		if (item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}

	@Override
	public Product add(Product item) throws DuplicateKeyException, InvalidDataException {
		if (item == null)
			throw new IllegalArgumentException();
		if (dao.findById(item.getIdProduct()).isPresent())
			throw new DuplicateKeyException();
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public Product change(Product item) throws NotFoundException, InvalidDataException {
		if (item == null)
			throw new IllegalArgumentException();
		if (dao.findById(item.getIdProduct()).isEmpty())
			throw new NotFoundException();
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public void delete(Product item) {
		if (item == null)
			throw new IllegalArgumentException();
		deleteById(item.getIdProduct());

	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
	
	@Override
	public <T> List<T> getPizzas(Class<T> type) {
	// TODO Auto-generated method stub
		return dao.findByType(Type.PIZZA, type);
	}

	@Override
	public <T> List<T> getEntrantes(Class<T> type) {
	// TODO Auto-generated method stub
	return dao.findByType(Type.STARTER, type);
	}

	@Override
	public <T> List<T> getBebidas(Class<T> type) {
	// TODO Auto-generated method stub
		return dao.findByType(Type.DRINK, type);
	}
}
