package com.app.crud.products;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/products")
public class ProductController {
	
	@GetMapping()
	public List<Product> getAllProducts() {
		Product p1 = new Product(1, "Lux", 25.34f);
		Product p2 = new Product(2, "Cinthol", 34.34f);
		List<Product> productList = new ArrayList<>();
		productList.add(p1);
		productList.add(p2);
		return productList;
	}

}
