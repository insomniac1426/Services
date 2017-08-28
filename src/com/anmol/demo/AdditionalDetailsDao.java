package com.anmol.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdditionalDetailsDao {

//	private static ResultSet res = null;
//	private static AdditionalDetails convertResToObject() throws SQLException{
//		AdditionalDetails ad = null;
//		if(res != null) {
//			while(res.next()) {
//				String username = res.getString(1);
//				int contNumber = res.getInt(2);
//				String postalLocation  = res.getString(3);
//				String postalCity= res.getString(4);
//				String postalState = res.getString(5);
//				String factoryLocation = res.getString(6);
//				String factoryCity = res.getString(7);
//				String factoryState = res.getString(8);
//				String department = res.getString(9);
//				ad = new AdditionalDetails(username, contNumber, postalLocation, postalCity, postalState, factoryLocation, factoryCity, factoryState, department);
//			}
//		}
//		return ad;
//	}
	
public static boolean insertIntoAdditionalDetails(AdditionalDetails add) throws SQLException {
		
		Connection conn = SQLConnection.getConnection();
		PreparedStatement st = conn.prepareStatement("INSERT INTO \"Customer_Additional_Details\" values(?,?,?,?,?,?,?,?,?,?,?,?)");
		st.setString(1, add.username);
		st.setInt(2, add.contNumber);
		st.setString(3, add.postalLocation);
		st.setString(4, add.postalCity);
		st.setString(5, add.postalState);
		st.setString(6, add.factoryLocation);
		st.setString(7, add.factoryCity);
		st.setString(8, add.factoryState);
		st.setBoolean(9, true);
		st.setString(10, add.department);
		st.setString(11, add.swift);
		st.setInt(12, add.accNumber);
		st.executeUpdate();
		return true;
	}
		
}
