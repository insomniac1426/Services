package com.anmol.demo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenericUserDaoImpl{

	private static ResultSet res = null;
	
	private static GenericUser convertResToObject() throws SQLException{
		GenericUser gu = null;
		if(res != null) {
			while(res.next()) {
				String uID = res.getString(1);
				String password = res.getString(2);
				boolean isConfirmed = res.getBoolean(3);
				boolean userType = res.getBoolean(4);
				boolean is_Bank_User = res.getBoolean(5);
				
				
				gu = new GenericUser(uID, password, isConfirmed, userType,is_Bank_User);
			}
		}
		return gu;
	}
	
	public static GenericUser searchUser(String username) throws SQLException{
		res = null;
		Connection conn = SQLConnection.getConnection();
		if(conn != null) {
			Statement st = conn.createStatement();
			String queryString = "select * from  \"User\" where Username='" + username + "'";
			res = st.executeQuery(queryString);
			GenericUser gu = convertResToObject();
			return gu;
		}
		return null;
	}
	
	
public static boolean insertIntoUser(GenericUser gu) throws SQLException {
		
		//if(checkUsernameExistence(c.email)) { return false; }
		
		Connection conn = SQLConnection.getConnection();
		PreparedStatement st = conn.prepareStatement("INSERT INTO \"User\" values" + "(?,?,?,?,?)");
		st.setString(1, gu.getUsername());
		st.setString(2, gu.getPassword());
		st.setBoolean(3, gu.isActive());
		st.setBoolean(4, gu.isConfirmed());
		st.setBoolean(5, gu.get_is_Bank_User());
		st.executeUpdate();
		
		return true;
	}
}
