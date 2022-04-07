package com.bootcamp.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.domains.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	<T> List<T> findByIdCommentIsNotNull(Class<T> type);

	<T> Iterable<T> findByIdCommentIsNotNull(Sort sort, Class<T> type);

	<T> Page<T> findByIdCommentIsNotNull(Pageable pageable, Class<T> type);
}
