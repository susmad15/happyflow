package com.ssischaefer.happyflow.controller;

import java.util.List;

import com.ssischaefer.happyflow.rest.resource.ContainerResource;

public class ContainerController {

	public static void insertProductIntoContainer(String containerID, int productID, int productAmount) {
		ContainerResource.insertProductIntoContainer(containerID, productID, productAmount);
	}
	
	public static List<String> getContainerIDs() {
		return ContainerResource.getContainerIDs();
	}

	public static void insertProductInBadQualityContainer(int productID, int productAmount) {
		ContainerResource.insertProductInBadQualityContainer(productID, productAmount);
	}

	public static String getBadQualityContainerID(int productID) {
		return ContainerResource.getBadQualityContainerID(productID);
	}
	
	public static void insertProductInWrongProductContainer(int productID, int productAmount) {
		ContainerResource.insertProductInWrongProductContainer(productID, productAmount);
	}
	
	public static String getWrongProductContainerID(int productID) {
		return ContainerResource.getWrongProductContainerID(productID);
	}

}
