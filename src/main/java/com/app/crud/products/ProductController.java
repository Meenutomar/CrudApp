package com.app.crud.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

    /*
     * Returns all products.
     */
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    /*
     * Creates a product
     */
    @PostMapping
    public ProductResponse addProduct(@RequestBody ProductRequest product) {
        
    	return productService.createProduct(product);
    }

    /*
     * Returns product by id.
     */
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable int id) {
    	return productService.getProductById(id);
    }

   
    /*
     *  Update product by id.
     */
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }
  
    /*
     * Delete product for the given id.
     */
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "Product deleted with id: " + id;
    }
}
