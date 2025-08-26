package com.app.crud.products;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	 @Autowired
	 private ProductRepository productRepository;
	 
	 public List<ProductResponse> getAllProducts() {
		 final List<ProductResponse> productDtos = new ArrayList<>();
		 final List<Product> products = productRepository.findAll();
		 //Controller - Service -Repo/Backend
		 			//DTO      Entity/Model
		 for (Product product : products) {
			productDtos.add(new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice()));
			
		 }
		 return productDtos;
	 }
	 
	 public ProductResponse getProductById(int id) {
	     Product product =  productRepository.findById(id).orElse(null);
	     return new ProductResponse(product.getId(), product.getName(), product.getDescription(),product.getPrice());
	 }
	 
	 public ProductResponse createProduct(ProductRequest productRequest) {
		Product reqProduct = new Product();
		reqProduct.setDescription(productRequest.getDescription());
		reqProduct.setName(productRequest.getName());
		reqProduct.setPrice(productRequest.getPrice());
		
 		Product product = productRepository.save(reqProduct);
		return new ProductResponse(product.getId(), product.getName(), product.getDescription(),product.getPrice());

		
		
	 }

}

