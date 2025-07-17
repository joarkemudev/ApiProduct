package com.joarkemu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joarkemu.model.Product;

/**
*
* @author JG
*/
@Repository
public class ProductRepository {
	@Autowired
	private ProductCrudRepository productCrudRepository;

	public List<Product> findAll(){
		return (List<Product>) productCrudRepository.findAll();
	}
	
	public Optional<Product> findById(int id) {
		return productCrudRepository.findById(id);
	}
	
	public Product save(Product product) {
		return productCrudRepository.save(product);
	}
}
