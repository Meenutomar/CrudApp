package com.app.crud.products;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductRequest {
	
	@NotBlank(message = "Name must be given")
	@Size(min = 3, max = 100, message= "Name must be 3 characters long and less than 100 characters")
	private String name;
	
	private String description;
	
	@NotNull(message = "Price must be gievn")
	@DecimalMin(value = "0.01", message = "Price must be greater than 0")
	private Float price;
	
	
	public ProductRequest() {
		super();
	}
	
	
	public ProductRequest(String name, String description, Float price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	
	

}
