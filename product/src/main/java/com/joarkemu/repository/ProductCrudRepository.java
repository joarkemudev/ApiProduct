package com.joarkemu.repository;

import org.springframework.data.repository.CrudRepository;

import com.joarkemu.model.Product;

/**
*
* @author JQ
*/
public interface ProductCrudRepository extends CrudRepository<Product, Integer> {
	
}
