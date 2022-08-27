package com.codingdojo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.models.Vehicle;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
	
	@SuppressWarnings("unchecked")
	Vehicle save(Vehicle vehicle);
	List<Vehicle> findAll();	
	Optional <Vehicle> findById(Long id);

}
