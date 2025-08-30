package com.app.crud.products;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.crud.files.FileStorageService;

import jakarta.validation.Valid;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductToProductResponseTransformer transformer;
	@Autowired
	private EmailService emailService;
	@Autowired
	private FileStorageService fileStorageService;

	/*
	 * Map request fields to product
	 */
	private void mapRequestToProduct(ProductRequest request, Product product) {
		product.setName(request.getName());
		product.setDescription(request.getDescription());
		product.setPrice(request.getPrice());
	}

	public List<ProductResponse> getAllProducts() {
		return productRepository.findAll().stream().map(transformer).toList();
	}

	public ProductResponse getProductById(int id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
		return transformer.apply(product);
	}

	public ProductResponse createProduct(ProductRequest productRequest) {
		Product reqProduct = new Product();
		mapRequestToProduct(productRequest, reqProduct);

		Product product = productRepository.save(reqProduct);
		sendCreationEmail(product);

		return transformer.apply(product);
	}

	public ProductResponse createProduct(ProductRequest productRequest, MultipartFile image) {
		Product reqProduct = new Product();
		mapRequestToProduct(productRequest, reqProduct);

		if (image != null && !image.isEmpty()) {
			String fileName = fileStorageService.storeFile(image);
			reqProduct.setImageName(fileName);
			reqProduct.setImagePath("/files/" + fileName);
		}

		Product product = productRepository.save(reqProduct);
		sendCreationEmail(product);

		return transformer.apply(product);
	}

	public ProductResponse updateProduct(int id, ProductRequest productRequest, MultipartFile image) {
		Product existingProduct = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id " + id));

		mapRequestToProduct(productRequest, existingProduct);

		if (image != null && !image.isEmpty()) {
			String fileName = fileStorageService.storeFile(image);
			existingProduct.setImageName(fileName);
			existingProduct.setImagePath("/files/" + fileName);
		}

		Product updated = productRepository.save(existingProduct);
		return transformer.apply(updated);
	}

	public void deleteProduct(int id) {
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
		} else {
			throw new RuntimeException("Product not found with id: " + id);
		}
	}

	public Product updateProduct(int id, Product product) {
		Product existingProduct = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id " + id));

		existingProduct.setName(product.getName());
		existingProduct.setDescription(product.getDescription());
		existingProduct.setPrice(product.getPrice());

		return productRepository.save(existingProduct);
	}

	private void sendCreationEmail(Product product) {
		emailService.sendEmail("meenutomar.jt@gmail.com", "New Product Created: " + product.getName(),
				"Product '" + product.getName() + "' has been created with ID: " + product.getId());
	}
}
