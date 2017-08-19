package com.anmol.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection{
	private static Connection conn = null;
	private static String USERNAME = "postgres";
	private static String PASSWORD = "admin";
	private static String URI = "jdbc:postgresql://localhost:5432/postgres";
	//private static String URI = "jdbc:oracle:thin:@localhost:1521:orcl";
	static {
		try {
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection(URI, USERNAME, PASSWORD);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private SQLConnection() {}
	public static Connection getConnection() {
		return conn;
	}
	
}
