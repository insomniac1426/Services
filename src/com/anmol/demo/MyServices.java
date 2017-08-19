package com.anmol.demo;

import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import java.security.SecureRandom;
import java.sql.SQLException;


@Path("/login")
public class MyServices {
	
	//@Produces(MediaType.TEXT_HTML) //Later response can redirect to the homepage.
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/checkCredentials/")
	public Response authenticateUser(@FormParam("username")String username, @FormParam("password")String password) throws SQLException {
		GenericUser gu = GenericUserDaoImpl.searchUser(username);
		String token = null;
		if(gu == null)	{ return null; }
		if(password.equals(gu.getPassword())) { 
			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[20];
			random.nextBytes(bytes);
			token = bytes.toString();
			Session s = new Session(token, gu.getUsername(), gu.getUserType());
			SessionDaoImpl.addSessionToTable(s);
			NewCookie nc1 = new NewCookie(new Cookie("username", username));
			NewCookie nc2 = new NewCookie(new Cookie("token", token));
			return Response.ok("Logged in successfully").cookie(nc1, nc2).build();  // Here we can redirect to the landing page
		}		
		return null;
	}
	
	@POST
	@Path("/validateToken/")
	public boolean checkToken(@CookieParam("token")String token, @CookieParam("username")String username) throws SQLException{
		return SessionDaoImpl.confirmSession(token, username);
	}  //Need to add an if for checkToken to restrict access before executing any service externally. Use singleton for internal checking.
	
	@POST
	@Path("/logout/")
	public boolean logoutUser(@CookieParam("token")String token, @CookieParam("username") String username) throws SQLException{
		if(!SessionDaoImpl.confirmSession(token, username)){ return false; }
		SessionDaoImpl.deleteSessionFromTable(username);
		return true;
	}
	
	@GET
	@Path("/logouttest/")
	public boolean logutUser(@QueryParam("token")String token, @QueryParam("username") String username) throws SQLException{
		if(!SessionDaoImpl.confirmSession(token, username)){ return false; }
		SessionDaoImpl.deleteSessionFromTable(username);
		return true;
	}
		
}
