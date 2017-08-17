package com.anmol.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection{
	private static Connection conn = null;
	private static String USERNAME = "hr";
	private static String PASSWORD = "hr";
	static {
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", USERNAME, PASSWORD);
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
