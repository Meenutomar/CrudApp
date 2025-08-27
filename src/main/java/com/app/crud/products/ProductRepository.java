package com.app.crud.products;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    // This method checks if a product with the given name already exists
    boolean existsByName(String name);
}
