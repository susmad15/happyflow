package com.ssischaefer.happyflow.data;

public class Package {

	private String packageID;
	private String palletID;
	private int productID;
	private int productAmount;
	private String containerID;

	public Package(String packageID, String palletID, int productID, int productAmount, String containerID) {
		this.packageID = packageID;
		this.palletID = palletID;
		this.productID = productID;
		this.productAmount = productAmount;
		this.containerID = containerID;
	}

	public String getPackageID() {
		return packageID;
	}

	public void setPackageID(String id) {
		this.packageID = id;
	}

	public String getPalletID() {
		return palletID;
	}

	public void setPalletID(String palletID) {
		this.palletID = palletID;
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

	public String getContainerID() {
		return containerID;
	}

	public void setContainerID(String containerID) {
		this.containerID = containerID;
	}

	@Override
	public String toString() {
		return "Package [packageID=" + packageID + ", palletID=" + palletID + ", productID=" + productID
				+ ", productAmount=" + productAmount + ", containerID=" + containerID + "]";
	}

}
