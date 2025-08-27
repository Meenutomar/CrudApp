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
	 
	 @Autowired
	 private ProductToProductResponseTransformer transformer;
	 
	 @Autowired
	    private EmailService emailService; 
	 
	 public List<ProductResponse> getAllProducts() {
		 final List<ProductResponse> productDtos = new ArrayList<>();
		 final List<Product> products = productRepository.findAll();
		 //Controller - Service -Repo/Backend
		 			//DTO      Entity/Model
		 for (Product product : products) {
			productDtos.add(transformer.apply(product));
		 }
		 return productDtos;
	 }
	 
	 public ProductResponse getProductById(int id) {
	     Product product =  productRepository.findById(id).orElse(null);
	     return transformer.apply(product);
	 }
	 
	 public ProductResponse createProduct(ProductRequest productRequest) {
		Product reqProduct = new Product();
		reqProduct.setDescription(productRequest.getDescription());
		reqProduct.setName(productRequest.getName());
		reqProduct.setPrice(productRequest.getPrice());
		
 		Product product = productRepository.save(reqProduct);
 		  /*
 		   * Send email
 		   */
 	    emailService.sendEmail(
 	        "receiveremail@example.com",
 	        "New Product Created: " + product.getName(),
 	        "Product '" + product.getName() + "' has been created with ID: " + product.getId()
 	    );

 	    return transformer.apply(product);
 	}
 	
	
	 
	/*
	 * Update product
	 */
	 public Product updateProduct(int id, Product product) {
	     Product existingProduct = productRepository.findById(id)
	             .orElseThrow(() -> new RuntimeException("Product not found with id " + id));

	     existingProduct.setName(product.getName());
	     existingProduct.setDescription(product.getDescription());
	     existingProduct.setPrice(product.getPrice());
	     return productRepository.save(existingProduct);
	 }
	 
	/*
	 * delete method
	 */
	    public void deleteProduct(int id) {
	        if (productRepository.existsById(id)) {
	            productRepository.deleteById(id);
	        } else {
	            throw new RuntimeException("Product not found with id: " + id);
	        }
	    }

}

