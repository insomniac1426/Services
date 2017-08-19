package com.anmol.demo;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;


@Path("/login")
public class MyService {
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/checkCredentials/")
	public boolean authenticateUser(@FormParam("username")String username, @FormParam("password")String password) throws SQLException {
		GenericUser gu = GenericUserDaoImpl.searchUser(username);
		if(gu == null)	return false;
		if(password.equals(gu.getPassword())) { return true; }		
		return false;
	}
	
	@Context private HttpServletRequest request;
	@GET
	@Path("/test")
	public String authenticate() {
		return request.getSession().getId();
	}
	
	
	
	
	
	
}
