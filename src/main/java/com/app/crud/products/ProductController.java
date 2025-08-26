package com.app.crud.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

    // Get all products
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    // Add a new product
    @PostMapping
    public ProductResponse addProduct(@RequestBody ProductRequest product) {
        
    	return productService.createProduct(product);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable int id) {
    	return productService.getProductById(id);
    }

    // Update product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        product.setId(id);
       // return productRepository.save(product);
        return null;
    }

    // Delete product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
       // productRepository.deleteById(id);
        return "Product deleted with id: " + id;
    }
}
