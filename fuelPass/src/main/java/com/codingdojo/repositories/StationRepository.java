package com.codingdojo.repositories;

import java.util.Optional;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.models.Station;


@Repository
public interface StationRepository extends CrudRepository<Station, Long> {
	
	Optional <Station> findByEmail(String email);
	
//	@SuppressWarnings ("unchecked" )
//	@Transactional(dontRollbackOn=Propagation.REQUIRES_NEW);
//	Station save(Station);
//	
	@SuppressWarnings("unchecked")
	Station save(Station station);

}
