package com.app.crud.products;

public class ProductResponse {

	private int id;
	private String name;
	private String description;
	private float price;
	private String imageName;
	private String imagePath;
    private String imageUrl;
    
	
	public ProductResponse() {
		super();
	}

	public ProductResponse(int id, String name, String description, float price,String imageName,String imagePath,String imageUrl ) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imageName = imageName;
		this.imagePath = imagePath;
		this.imageUrl = imageUrl;


		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	
	
}
