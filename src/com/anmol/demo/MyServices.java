package com.anmol.demo;





import javax.ws.rs.Consumes;
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
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;


@Path("/login")
public class MyServices {
	
	//@Produces(MediaType.TEXT_HTML) //Later response can redirect to the homepage.
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/checkCredentials/")
	public Response authenticateUser(String data) throws SQLException, JSONException {
		JSONObject inputJsonObj = new JSONObject(data);
		String username = inputJsonObj.getString("username");
		String password = inputJsonObj.getString("password");;
		System.out.println(username);
		GenericUser gu = GenericUserDaoImpl.searchUser(username);
		String token = null;
		if(gu == null)	{ return Response.serverError().build(); }
		if(password.equals(gu.getPassword())) { 
			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[20];
			random.nextBytes(bytes);
			token = bytes.toString();
			Session s = new Session(token, gu.getUsername(), gu.getUserType());
			SessionDaoImpl.addSessionToTable(s);
			NewCookie nc1 = new NewCookie(new Cookie("username", gu.getUsername()));
			NewCookie nc2 = new NewCookie(new Cookie("token", s.token));
			NewCookie nc3 = new NewCookie(new Cookie("userType",gu.getUserType()));
			return Response.ok("Logged in Successfully").cookie(nc1, nc2, nc3).build();  // Here we can redirect to the landing page
		}		
		return Response.serverError().build();
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
	
	@POST
	@Path("/signupCustomer/")
	public boolean signUpCustomer(@FormParam("name")String name, @FormParam("email") String email, @FormParam("password")String password) throws SQLException{
		Customer c = new Customer(name, email, password);
		if(CustomerDaoImpl.insertIntoCustomers(c)) {
			return true;
		}
		return false;
	}
	
	@GET
	@Path("/fetchCustomerDetails/")
	public String fetchCustomerDetails(@CookieParam("token")String token, @CookieParam("username") String username) throws SQLException, JsonGenerationException, JsonMappingException, IOException{
		Customer c = CustomerDaoImpl.searchUser(username);
		if(c == null)	{ return null; }
		return c.convertObjectToJSON();
	}
		
}
