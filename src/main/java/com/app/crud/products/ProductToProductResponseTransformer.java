package com.app.crud.products;

import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class ProductToProductResponseTransformer implements Function<Product, ProductResponse> {

    @Override
    public ProductResponse apply(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setImageName(product.getImageName());
        response.setImagePath(product.getImagePath());

        if (product.getImageName() != null) {
            response.setImageUrl("http://localhost:8080/files/" + product.getImageName());
        }

        return response;
    }
}
