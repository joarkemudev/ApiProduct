package com.joarkemu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joarkemu.repository.ProductRepository;
import com.joarkemu.model.Product;
/**
*
* @author JQ
*/

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAll(){
		return productRepository.findAll();
	}
	
	public Optional<Product> findById(int id) {
		return productRepository.findById(id);
		
	}
	
	public Product save(Product product) {
		if (product.getId() == null) {
			return productRepository.save(product);
		}else {
			Optional<Product> paux=productRepository.findById(product.getId());
			if (paux.isEmpty()) {
				return productRepository.save(product);
			} else {
				return product;
			}
		}
	}
}