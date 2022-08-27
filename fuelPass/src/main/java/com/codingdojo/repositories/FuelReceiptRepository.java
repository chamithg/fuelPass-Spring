package com.codingdojo.repositories;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.models.FuelReceipt;


@Repository
public interface FuelReceiptRepository extends CrudRepository<FuelReceipt, Long> {

	List<FuelReceipt> findAll();

}
