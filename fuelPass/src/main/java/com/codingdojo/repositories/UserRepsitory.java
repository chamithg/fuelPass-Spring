package com.codingdojo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.models.User;


@Repository
public interface UserRepsitory extends CrudRepository<User, Long> {
	
	Optional <User> findByEmail(String email);
	List<User> findAll();

}
