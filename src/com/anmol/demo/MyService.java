package com.anmol.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;


@Path("/login")
public class MyService {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/first")
	public String print() {
		return "Hello";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/checkCredentials/{username}/{password}")
	public boolean authenticateUser(@PathParam("username")String username, @PathParam("password")String password) throws SQLException {
		GenericUser gu = MyJDBCOperations.searchUser(username);
		//System.out.println(gu);
		//System.out.println(username);
		if(gu == null) {
			return false;
		}
		
		if(password.equals(gu.getPassword())) {
			return true;
		}		
		return false;
	}
	
	
	
}
