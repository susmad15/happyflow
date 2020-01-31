package com.ssischaefer.happyflow.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.ssischaefer.happyflow.data.Package;
import com.ssischaefer.happyflow.rest.resource.PackageResource;

public class PackageController {

	private static List<String> packageIDs;
	private static List<String> palletIDs;

	static {
		packageIDs = new ArrayList<String>();
		palletIDs = new ArrayList<String>();
	}
	
	public static void insertPackage(String packageID, String palletID, int productID, int productAmount) {
		PackageResource.insertPackage(packageID, palletID, productID, productAmount);
	}
	
	public static void deletePackage(String packageID) {
		PackageResource.deletePackage(packageID);
	}
	
	public static void updateProductAmount(String packageID, int productAmount) {
		PackageResource.updateProductAmount(packageID, productAmount);
	}

	public static Package getPackage(String packageID) {
		Package pa = PackageResource.getPackage(packageID);

		return pa;
	}

	public static List<String> getPackageIDs(String palletID) {
		JSONArray jsonResponse = new JSONArray(PackageResource.getPackageIDs(palletID));

		try {
			for (int i = 0; i < jsonResponse.length(); i++) {
				packageIDs.add(jsonResponse.getString(i));
			}

			return packageIDs;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<String> getPalletIDs() {
		JSONArray jsonResponse = new JSONArray(PackageResource.getPalletIDs());

		try {
			for (int i = 0; i < jsonResponse.length(); i++) {
				palletIDs.add(jsonResponse.getString(i));
			}

			return palletIDs;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static int getProductAmount(String packageID) {
		return PackageResource.getProductAmount(packageID);
	}

	public static int getProductID(String packageID) {
		return PackageResource.getProductID(packageID);
	}

}
