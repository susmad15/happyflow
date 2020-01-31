package com.ssischaefer.happyflow.controller;

import com.ssischaefer.happyflow.data.Product;
import com.ssischaefer.happyflow.rest.resource.ProductResource;

public class ProductController {

	public static void changeProductAmount(String packageID, int productAmount) {
		ProductResource.changeProductAmount(packageID, productAmount);
	}
	
	public static Product getProduct(int productID) {
		return ProductResource.getProduct(productID);
	}
	
}
