package com.app.crud.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	

    @Autowired
    private EmailService emailService; 

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
    
    //@PostMapping
    //public ProductResponse addProduct(@Valid @RequestBody ProductRequest product) {
       
    	//return productService.createProduct(product);
    //}
    	

    @PostMapping
    public ProductResponse addProduct(@Valid @RequestBody ProductRequest product) {
        /*
         * Create the product using service
         */
        ProductResponse createdProduct = productService.createProduct(product);

        /*
         * Send email after product creation
         */
        String recipient = "chaitri.nirvytech@gmail.com";
        String subject = "New Product Created: " + createdProduct.getName();
        String body = "Product '" + createdProduct.getName() + "' has been successfully created with ID: " + createdProduct.getId();

        emailService.sendEmail(recipient, subject, body);

        /*
         * Return created product
         */
        return createdProduct;
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
