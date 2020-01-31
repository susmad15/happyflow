package com.ssischaefer.happyflow.data;

public class Product {

	private int productID;
	private String productName;
	private String productImage;

	public Product(int productID, String productName, String productImage) {
		this.productID = productID;
		this.productName = productName;
		this.productImage = productImage;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", productImage=" + productImage
				+ "]";
	}

}
