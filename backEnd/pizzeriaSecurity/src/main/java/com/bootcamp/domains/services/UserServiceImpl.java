package com.bootcamp.domains.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bootcamp.domains.contracts.repositories.UserRepository;
import com.bootcamp.domains.contracts.services.UserService;
import com.bootcamp.domains.entities.User;
import com.bootcamp.exceptions.DuplicateKeyException;
import com.bootcamp.exceptions.InvalidDataException;
import com.bootcamp.exceptions.NotFoundException;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository dao;

	public UserServiceImpl(UserRepository dao) {
		this.dao = dao;
	}

	@Override
	public List<User> getAll() {
		return dao.findAll();
	}

	@Override
	public Iterable<User> getAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<User> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByUsernameIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByUsernameIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByUsernameIsNotNull(pageable, type);
	}

	@Override
	public User getOne(String id) throws NotFoundException {
		var item = dao.findById(id);
		if (item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}

	@Override
	public User add(User item) throws DuplicateKeyException, InvalidDataException {
		if (item == null)
			throw new IllegalArgumentException();
		if (dao.findById(item.getUsername()).isPresent())
			throw new DuplicateKeyException();
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public User change(User item) throws NotFoundException, InvalidDataException {
		if (item == null)
			throw new IllegalArgumentException();
		if (dao.findById(item.getUsername()).isEmpty())
			throw new NotFoundException();
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public void delete(User item) {
		if (item == null)
			throw new IllegalArgumentException();
		deleteById(item.getUsername());

	}

	@Override
	public void deleteById(String id) {
		dao.deleteById(id);
	}

}
