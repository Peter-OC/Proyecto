package com.capgemini.domains.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capgemini.domains.contracts.repositories.CommentRepository;
import com.capgemini.domains.contracts.services.CommentService;
import com.capgemini.domains.entities.Comment;
import com.capgemini.exceptions.DuplicateKeyException;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.exceptions.NotFoundException;

@Service
public class CommentServiceImpl implements CommentService {
	private CommentRepository dao;

	public CommentServiceImpl(CommentRepository dao) {
		this.dao = dao;
	}

	@Override
	public List<Comment> getAll() {
		return dao.findAll();
	}

	@Override
	public Iterable<Comment> getAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Comment> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByIdCommentIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByIdCommentIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByIdCommentIsNotNull(pageable, type);
	}

	@Override
	public Comment getOne(Integer id) throws NotFoundException {
		var item = dao.findById(id);
		if (item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}

	@Override
	public Comment add(Comment item) throws DuplicateKeyException, InvalidDataException {
		if (item == null)
			throw new IllegalArgumentException();
		if (dao.findById(item.getIdComment()).isPresent())
			throw new DuplicateKeyException();
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public Comment change(Comment item) throws NotFoundException, InvalidDataException {
		if (item == null)
			throw new IllegalArgumentException();
		if (dao.findById(item.getIdComment()).isEmpty())
			throw new NotFoundException();
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public void delete(Comment item) {
		if (item == null)
			throw new IllegalArgumentException();
		deleteById(item.getIdComment());

	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
}
