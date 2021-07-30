package com.hcl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcl.model.Product;
import com.hcl.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository repo;
	
	public List<Product> getAllProducts() {
		List<Product> empRecords = new ArrayList<>();
		repo.findAll().forEach(empRecords::add);
		return empRecords;
	}

	public void addProduct(Product product) {
		repo.save(product);
	}
	
	public Product getProductById(long id) throws Exception {
		Optional<Product> emp=repo.findById(id);
		return emp.orElseThrow(()->new Exception("Couldn't find the record id"+id));
	}
	
	
	public Product updateProduct(long id, Product product)throws Exception {
		Product p1= getProductById(id);
		p1.setName(product.getName());
		repo.save(p1);
		return p1;
	}
	
	public void deleteProduct(long id) {
		repo.deleteById(id);
	}
}
