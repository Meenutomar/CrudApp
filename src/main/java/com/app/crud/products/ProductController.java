package com.app.crud.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;


import jakarta.validation.Valid;

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


/*
 *  Create Product with Image Upload
 
@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<ProductResponse> createProductWithImage(
        @RequestPart("product") @Valid String productId,
        @RequestPart(value = "image", required = false) MultipartFile image)
        {
    System.out.println("Product ID::" + productId);
    return ResponseEntity.ok(productService.createProduct(productRequest, image));
}
*/


 @Autowired
private ObjectMapper objectMapper;

@PreAuthorize("hasRole('ADMIN')")
@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<ProductResponse> createProductWithImage(
        @RequestPart("product") String productJson,
        @RequestPart(value = "image", required = false) MultipartFile image) throws Exception {

    ProductRequest productRequest = objectMapper.readValue(productJson, ProductRequest.class);
    return ResponseEntity.ok(productService.createProduct(productRequest, image));
}
 


//Update product with/without image
@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<ProductResponse> updateProduct(
     @PathVariable int id,
     @RequestPart("product") @Valid ProductRequest productRequest,
     @RequestPart(value = "image", required = false) MultipartFile image) {
 return ResponseEntity.ok(productService.updateProduct(id, productRequest, image));
}
}


