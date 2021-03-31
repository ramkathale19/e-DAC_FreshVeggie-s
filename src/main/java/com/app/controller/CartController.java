package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojos.Product;
import com.app.service.ProductServiceImpl;

public class CartController {

	@GetMapping("/addtocart")
	public String addToCart(@RequestParam int pid, HttpSession session) {
		List<Integer> cart = (List<Integer>) session.getAttribute("cart");
		cart.add(pid);
		return "/product/list";
	}
	
	
	
	  @GetMapping("/showcart")
	  
	  public String showCart(HttpSession session) {
	  List<Integer> cart = (List<Integer>) session.getAttribute("cart");
	  List<Product> products = new ArrayList<Product>(); double total = 0; 
	  for
	  (Integer id : cart) { Product p = productService.getById(id);
	  total + =  p.getPrice(); products.add(p);
	  
	  }
	  session.setAttribute("showcart", products); session.setAttribute("price", total); 
	  return "/customer/showcart"; 
	  }
	 
}
