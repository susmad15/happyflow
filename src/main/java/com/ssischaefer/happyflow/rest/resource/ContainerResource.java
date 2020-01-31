package com.ssischaefer.happyflow.rest.resource;

import java.util.List;

import com.ssischaefer.happyflow.database.proxy.ContainerProxy;

public class ContainerResource {

	public static void insertProductIntoContainer(String containerID, int productID, int productAmount) {
		ContainerProxy.insertProductInContainer(containerID, productID, productAmount);
	}
	
	public static List<String> getContainerIDs() {
		return ContainerProxy.getContainerIDs();
	}
	
	public static void insertProductInBadQualityContainer(int productID, int productAmount) {
		ContainerProxy.insertProductInBadQualityContainer(productID, productAmount);
	}
	
	public static String getBadQualityContainerID(int productID) {
		return ContainerProxy.getBadQualityContainerID(productID);
	}
	
	public static void insertProductInWrongProductContainer(int productID, int productAmount) {
		ContainerProxy.insertProductInWrongProductContainer(productID, productAmount);
	}
	
	public static String getWrongProductContainerID(int productID) {
		return ContainerProxy.getWrongProductContainerID(productID);
	}
	
}
