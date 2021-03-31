package com.app.service;

import java.util.List;

import com.app.pojos.Product;

public interface IProductService {

	public Product addProduct(Product product);
	
	public List<Product> findAllProducts() ;

	void deleteProduct(Integer Id);
	
	
}
