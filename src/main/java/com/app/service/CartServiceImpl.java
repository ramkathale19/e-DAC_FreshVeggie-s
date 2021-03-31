package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ICartDao;
import com.app.pojos.Product;

@Service
@Transactional
public class CartServiceImpl implements ICartService {

	@Autowired
	private ICartDao cartDao;
	
	public List<Product> getProductByCart(String cart) {
		// TODO Auto-generated method stub
		return cartDao.findAll();
	}


