package com.anmol.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.sql.SQLException;


@Path("/login")
public class MyServices {
	
	//@Produces(MediaType.TEXT_HTML) //Later response can redirect to the homepage.
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/checkCredentials/")
	public Response authenticateUser(String data, @Context HttpServletRequest request) throws SQLException, JSONException {
		JSONObject inputJsonObj = new JSONObject(data);
		String username = inputJsonObj.getString("username");
		String password = inputJsonObj.getString("password");
		GenericUser gu = GenericUserDaoImpl.searchUser(username);
		if(gu == null)	{ return Response.serverError().status(Status.EXPECTATION_FAILED).build(); }
		if(password.equals(gu.getPassword())) {
			Session s = new Session(gu.getUsername(), gu.getUserType());
			HttpSession hs = request.getSession();//CREATE A SESSION FOR THE USER.
			hs.setAttribute("session", s);
			return Response.ok("Logged in Successfully").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();  // Here we can redirect to the landing page
		}		
		return Response.serverError().status(Status.EXPECTATION_FAILED).build();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/getUserInfo/")
	public Response checkToken(@Context HttpServletRequest request) throws SQLException{
		Session s = SessionUtility.sessionValidation(request);
		if(s != null) {
			String json = "{'username': '" + s.getUserId() + "', 'userType': '" + s.userType + "'}";
			return Response.ok(json).status(Status.OK).build();
		}
		return Response.serverError().status(Status.EXPECTATION_FAILED).build();
	}
	
//	@POST
//	@Path("/logout/")
//	public boolean logoutUser(@Context HttpServletRequest request) throws SQLException{
//		
//		return true;
//	}
//	

	
@POST
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
@Path("/signupCustomer/")
public Response signUpCustomer(String data, @Context HttpServletRequest request) throws SQLException, JSONException{
	JSONObject inputJsonObj = new JSONObject(data);
	String name = inputJsonObj.getString("username");
	String email = inputJsonObj.getString("email");
	String password = inputJsonObj.getString("password");
	
	Customer c = new Customer(email, name, password);
	GenericUser gu = new GenericUser(email,password,lastLogin,"cust",0,0);
	System.out.println(c);
	
	if(CustomerDaoImpl.insertIntoCustomers(c)) {
		return Response.ok("hi").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();
	}
	return Response.serverError().status(Status.EXPECTATION_FAILED).build();
}

//	
//	@GET
//	@Path("/fetchCustomerDetails/")
//	public String fetchCustomerDetails(@Context HttpServletRequest request) throws SQLException, JsonGenerationException, JsonMappingException, IOException{
//		Customer c = CustomerDaoImpl.searchUser(username);
//		if(c == null)	{ return null; }
//		return c.convertObjectToJSON();
//	}
		
}
