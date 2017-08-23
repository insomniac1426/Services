package com.anmol.demo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDaoImpl {
	private static ResultSet res = null;
	
	private static Customer convertResToObject() throws SQLException{
		Customer gu = null;
		if(res != null) {
			while(res.next()) {
				String name = res.getString(1);
				String email = res.getString(2);
				String password = res.getString(3);
				gu = new Customer(name, email, password);
			}
		}
		return gu;
	}
	
	public static boolean insertIntoCustomers(Customer c) throws SQLException {
		
		//if(checkUsernameExistence(c.email)) { return false; }
		
		Connection conn = SQLConnection.getConnection();
		PreparedStatement st = conn.prepareStatement("INSERT INTO customers values" + "(?,?,?)");
		st.setString(1, c.name);
		st.setString(2, c.email);
		st.setString(3, c.password);
		st.executeUpdate();
		return true;
	}
	
	public static boolean checkUsernameExistence(String email) throws SQLException{
		res = null;
		Connection conn = SQLConnection.getConnection();
		if(conn != null) {
			Statement st = conn.createStatement();
			String queryString = "select * from customers where email='" + email + "'";
			res = st.executeQuery(queryString);
		}
		
		if(res.next())	return true;
		return false;
	}
	
	public static Customer searchUser(String username) throws SQLException{  //username is email here
		res = null;
		Connection conn = SQLConnection.getConnection();
		if(conn != null) {
			Statement st = conn.createStatement();
			String queryString = "select * from customers where useremail='" + username + "'";
			res = st.executeQuery(queryString);
			Customer gu = convertResToObject();
			return gu;
		}
		return null;
	}
	
	
}
