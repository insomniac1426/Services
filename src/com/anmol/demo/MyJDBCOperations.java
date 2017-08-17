package com.anmol.demo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyJDBCOperations {

	private static ResultSet res = null;
	
	
	
	static public GenericUser searchUser(String username) throws SQLException{
		Connection conn = SQLConnection.getConnection();
		if(conn != null) {
			Statement st = conn.createStatement();
			String queryString = "select * from GenUserTable where UserID='" + username + "'";
			
			res = st.executeQuery(queryString);
			GenericUser gu = null;
			if(res != null) {
				System.out.println(queryString);
				while(res.next()) {
					String uID = res.getString(1);
					String password = res.getString(2);
					Date lastLogin = res.getDate(3);
					String userType = res.getString(4);
					int isActive = res.getInt(5);
					int isConfirmed = res.getInt(6);
					gu = new GenericUser(uID, password, lastLogin, userType, isActive, isConfirmed);
					System.out.println(gu);
				}
			}
			return gu;
		}
		return null;
	}
}
