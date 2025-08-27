	package com.app.crud.products;

	import jakarta.validation.ConstraintValidator;
	import jakarta.validation.ConstraintValidatorContext;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

	@Service
	public class UniqueProductNameContraintValidator implements ConstraintValidator<UniqueProductNameConstraint, String> {

	    @Autowired
	    private ProductRepository productRepository;

	    @Override
	    public boolean isValid(String productName, ConstraintValidatorContext context) {
	        if (productName == null || productName.isEmpty()) {
	            return true; // Let @NotBlank handle null/empty cases
	        }

	        // Check if product with this name exists
	        return !productRepository.existsByName(productName);
	    }
	}



