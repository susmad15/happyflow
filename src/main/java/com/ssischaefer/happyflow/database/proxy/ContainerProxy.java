package com.ssischaefer.happyflow.database.proxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ssischaefer.happyflow.database.OracleDB;

public class ContainerProxy {
	
	private static List<String> containerIDs;

	
	// Create container
	public static void createContainer(String containerID, int productID, int productAmount) {
		try {
			PreparedStatement stmt = OracleDB.getConnection()
					.prepareStatement("UPDATE container SET productID = ?, productAmount = ? WHERE containerID = ?");

			stmt.setString(1, containerID);
			stmt.setInt(2, productID);
			stmt.setInt(3, productAmount);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// Insert product into container
	public static void insertProductInContainer(String containerID, int productID, int productAmount) {
		try {
			PreparedStatement stmt = OracleDB.getConnection()
					.prepareStatement("UPDATE container SET productID = ?, productAmount = ? WHERE containerID = ?");

			stmt.setString(1, containerID);
			stmt.setInt(2, productID);
			stmt.setInt(3, productAmount);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	// Receive container ids
	public static List<String> getContainerIDs() {
		containerIDs = new ArrayList<String>();
		
		try {
			Statement stmt = OracleDB.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM container");
			
			while(rs.next()) {
				containerIDs.add(rs.getString("containerID"));
			}
			
			return containerIDs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	// Insert product into bad quality container
	public static void insertProductInBadQualityContainer(int productID, int productAmount) {
		try {
			Statement stmtContains = OracleDB.getConnection().createStatement();
			ResultSet rsContains = stmtContains
					.executeQuery("SELECT * FROM badqualitycontainer WHERE productID = " + productID);
			Statement stmtNext = OracleDB.getConnection().createStatement();
			ResultSet rsNext = stmtNext.executeQuery("SELECT * FROM badqualitycontainer WHERE productID = 0");
			PreparedStatement stUpdate = OracleDB.getConnection().prepareStatement(
					"UPDATE badQualityContainer SET productID = ?, productAmount = ? WHERE badQualityContainerID = ?");

			if (!rsContains.next()) {
				rsNext.next();

				stUpdate.setString(1, rsNext.getString("badQualityContainerID"));
				stUpdate.setInt(2, productID);
				stUpdate.setInt(3, productAmount);

				stUpdate.executeQuery();

				stmtContains.close();
				stmtNext.close();
				stUpdate.close();
				rsContains.close();
				rsNext.close();
			} else {
				stUpdate.setString(1, rsNext.getString("badQualityContainerID"));
				stUpdate.setInt(2, productID);
				stUpdate.setInt(3, productAmount);

				stUpdate.executeQuery();

				stmtContains.close();
				stmtNext.close();
				stUpdate.close();
				rsContains.close();
				rsNext.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	// Receive bad quality container id through product id
	public static String getBadQualityContainerID(int productID) {
		try {
			Statement stmtContains = OracleDB.getConnection().createStatement();
			ResultSet rsContains = stmtContains
					.executeQuery("SELECT * FROM badqualitycontainer WHERE productID = " + productID);
			Statement stmtNext = OracleDB.getConnection().createStatement();
			ResultSet rsNext = stmtNext.executeQuery("SELECT * FROM badqualitycontainer WHERE productID = 0");

			if (!rsContains.next()) {
				rsNext.next();

				return rsNext.getString("badQualityContainerID");
			} else {
				return rsContains.getString("badQualityContainerID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	
	// Insert product into wrong product container
	public static void insertProductInWrongProductContainer(int productID, int productAmount) {
		try {
			Statement stmtContains = OracleDB.getConnection().createStatement();
			ResultSet rsContains = stmtContains
					.executeQuery("SELECT * FROM wrongproductcontainer WHERE productID = " + productID);
			Statement stmtNext = OracleDB.getConnection().createStatement();
			ResultSet rsNext = stmtNext.executeQuery("SELECT * FROM wrongproductcontainer WHERE productID = 0");
			PreparedStatement stUpdate = OracleDB.getConnection().prepareStatement(
					"UPDATE wrongproductcontainer SET productID = ?, productAmount = ? WHERE wrongProductContainer = ?");

			if (!rsContains.next()) {
				rsNext.next();

				stUpdate.setString(1, rsNext.getString("wrongProductContainer"));
				stUpdate.setInt(2, productID);
				stUpdate.setInt(3, productAmount);

				stUpdate.executeQuery();

				stmtContains.close();
				stmtNext.close();
				stUpdate.close();
				rsContains.close();
				rsNext.close();
			} else {
				stUpdate.setString(1, rsNext.getString("wrongProductContainer"));
				stUpdate.setInt(2, productID);
				stUpdate.setInt(3, productAmount);

				stUpdate.executeQuery();

				stmtContains.close();
				stmtNext.close();
				stUpdate.close();
				rsContains.close();
				rsNext.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	// Receive wrong product container id through product id
	public static String getWrongProductContainerID(int productID) {
		try {
			Statement stmtContains = OracleDB.getConnection().createStatement();
			ResultSet rsContains = stmtContains
					.executeQuery("SELECT * FROM wrongproductcontainer WHERE productID = " + productID);
			Statement stmtNext = OracleDB.getConnection().createStatement();
			ResultSet rsNext = stmtNext.executeQuery("SELECT * FROM wrongproductcontainer WHERE productID = 0");

			if (!rsContains.next()) {
				rsNext.next();

				return rsNext.getString("wrongProductContainer");
			} else {
				return rsContains.getString("wrongProductContainer");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
