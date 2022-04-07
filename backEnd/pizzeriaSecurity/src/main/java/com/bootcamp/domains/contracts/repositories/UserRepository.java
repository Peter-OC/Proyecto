package com.bootcamp.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.domains.entities.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	<T> List<T> findByUsernameIsNotNull(Class<T> type); 
	<T> Iterable<T> findByUsernameIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByUsernameIsNotNull(Pageable pageable, Class<T> type);

}
