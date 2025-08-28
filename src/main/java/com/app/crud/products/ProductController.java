package com.app.crud.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
     * Get all products (ROLE_USER & ROLE_ADMIN dono access kar sakte hain)
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    /*
     *Get product by ID (ROLE_USER & ROLE_ADMIN dono access kar sakte hain)
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ProductResponse getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    /*
     * Create product ( ROLE_ADMIN)
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse addProduct(@Valid @RequestBody ProductRequest product) {
        ProductResponse createdProduct = productService.createProduct(product);

        // Email after creation
        String recipient = "meenutomar.jt@gmail.com";
        String subject = "New Product Created: " + createdProduct.getName();
        String body = "Product '" + createdProduct.getName() + "' has been successfully created with ID: " + createdProduct.getId();
        emailService.sendEmail(recipient, subject, body);

        return createdProduct;
    }

    /*
     *Update product (ROLE_ADMIN)
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    /*
     *Delete product ( ROLE_ADMIN)
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "Product deleted with id: " + id;
    }
}
