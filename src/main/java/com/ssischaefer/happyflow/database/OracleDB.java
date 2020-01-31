package com.ssischaefer.happyflow.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDB {

	private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final static String USERNAME = "C##PascalKlug";
	private final static String PASSWORD = "Pasci1234";

	private static Connection connection;

	public OracleDB() {
		connect();
	}
	
	
	// OracleDB connect
	public static void connect() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			if (connection != null) {
				System.out.println("[OracleDB] Connected...");
			} else {
				System.out.println("[OracleDB] Error 404");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// OracleDB disconnect
	public static void disconnect() {
		try {
			if(!connection.isClosed()) {
				connection.close();

				System.out.println("[OracleDB] Disconnected...");
			} else {
				System.out.println("[OracleDB] Error 404");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}

	public static void setConnection(Connection connection) {
		OracleDB.connection = connection;
	}

}
