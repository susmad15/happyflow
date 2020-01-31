package com.ssischaefer.happyflow.database.proxy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ssischaefer.happyflow.data.Product;
import com.ssischaefer.happyflow.database.OracleDB;

public class ProductProxy {

	
	// Change product amount
	public static void changeProductAmount(String packageID, int productAmount) {
		try {
			Statement stmt = OracleDB.getConnection().createStatement();
			
			stmt.executeQuery("UPDATE package SET productAmount=" + productAmount + " WHERE packageID='" + packageID + "'");

			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	// Receive product through product id
	public static Product getProduct(int productID) {
		try {
			Statement stmt = OracleDB.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM product WHERE productID = " + productID);

			rs.next();

			Product pr = new Product(rs.getInt("productID"), rs.getString("productName"), rs.getString("productImage"));

			stmt.close();
			rs.close();

			return pr;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
