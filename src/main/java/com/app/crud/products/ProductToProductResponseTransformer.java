package com.app.crud.products;

import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class ProductToProductResponseTransformer implements Function<Product, ProductResponse> {

	@Override
	public ProductResponse apply(Product product) {
		return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());

	}

}
