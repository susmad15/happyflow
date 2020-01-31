package com.ssischaefer.happyflow.database.proxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ssischaefer.happyflow.controller.ContainerController;
import com.ssischaefer.happyflow.data.Package;
import com.ssischaefer.happyflow.database.OracleDB;

public class PackageProxy {

	private static List<String> containerIDs;
	private static List<String> packageIDs;
	private static List<String> palletIDs;

	
	// Insert package
	public static void insertPackage(String packageID, String palletID, int productID, int productAmount) {
		try {
			Statement stmt = OracleDB.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM package WHERE packageid = '" + packageID + "'");
			rs.next();

			containerIDs = new ArrayList<String>(ContainerController.getContainerIDs());

			String containerid = "";
			int number = 0;
			Random random = new Random();

			boolean insertPackage = true;

			while (insertPackage) {
				number = random.nextInt(19999999 - 10000000 + 1) + 10000000;

				StringBuilder builder = new StringBuilder(String.valueOf(number));

				builder.insert(2, '.');
				builder.insert(6, '.');
				containerid = builder.toString();

				if (!containerIDs.contains(containerid)) {
					stmt.executeQuery("INSERT INTO container VALUES ('" + containerid + "', 0, 0)");

					insertPackage = false;
				}

				stmt.executeQuery("INSERT INTO package VALUES ('" + packageID + "', '" + palletID + "', " + productID
						+ ", " + productAmount + ", '" + containerid + "')");
				
				stmt.close();
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	// Delete package
	public static void deletePackage(String packageID) {
		try {
			PreparedStatement stmt = OracleDB.getConnection()
					.prepareStatement("DELETE FROM package WHERE packageID = ?");

			stmt.setString(1, packageID);

			stmt.executeUpdate();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	// Update product amount
	public static void updateProductAmount(String packageID, int productAmount) {
		try {
			PreparedStatement stmt = OracleDB.getConnection()
					.prepareStatement("UPDATE package SET productAmount = ? WHERE packageID = ?");

			stmt.setInt(1, productAmount);
			stmt.setString(2, packageID);

			stmt.executeUpdate();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	// Receive package through package id
	public static Package getPackage(String packageID) {
		try {
			Statement stmt = OracleDB.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM package WHERE packageID = '" + packageID + "'");

			rs.next();

			Package pa = new Package(rs.getString("packageID"), rs.getString("palletID"), rs.getInt("productID"),
					rs.getInt("productAmount"), rs.getString("containerID"));

			stmt.close();
			rs.close();

			return pa;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	
	// Receive package ids through pallet id
	public static List<String> getPackageIDs(String palletID) {
		packageIDs = new ArrayList<String>();

		try {
			Statement stmt = OracleDB.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM package WHERE palletID = '" + palletID + "'");

			while (rs.next()) {
				packageIDs.add(rs.getString("packageID"));
			}

			stmt.close();
			rs.close();

			return packageIDs;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	
	// Receive pallet ids
	public static List<String> getPalletIDs() {
		palletIDs = new ArrayList<String>();
		try {
			Statement stmt = OracleDB.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM package");

			while (rs.next()) {
				palletIDs.add(rs.getString("palletID"));
			}

			stmt.close();
			rs.close();

			return palletIDs;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	
	// Receive product amount through package id
	public static int getProductAmount(String packageID) {
		try {
			Statement stmt = OracleDB.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM package WHERE packageID = '" + packageID + "'");

			rs.next();

			int productAmount = rs.getInt("productAmount");

			stmt.close();
			rs.close();

			return productAmount;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	
	// Receive product id through package id
	public static int getProductID(String packageID) {
		try {
			Statement stmt = OracleDB.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM package WHERE packageID = '" + packageID + "'");

			rs.next();

			int productID = rs.getInt("productID");

			stmt.close();
			rs.close();

			return productID;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

}
