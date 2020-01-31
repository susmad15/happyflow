package com.ssischaefer.happyflow.data;

public class Container {

	private String containerID;
	private int productID;
	private int productAmount;

	public Container(String containerID, int productID, int productAmount) {
		this.containerID = containerID;
		this.productID = productID;
		this.productAmount = productAmount;
	}

	public String getContainerID() {
		return containerID;
	}

	public void setContainerID(String containerID) {
		this.containerID = containerID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}

	@Override
	public String toString() {
		return "Container [containerID=" + containerID + ", productID=" + productID + ", productAmount=" + productAmount
				+ "]";
	}

}
