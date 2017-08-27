package com.anmol.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;


@Path("/login")
public class MyServices {
	
	//@Produces(MediaType.TEXT_HTML) //Later response can redirect to the homepage.
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/checkCredentials/")
	public Response authenticateUser(String data, @Context HttpServletRequest request) throws SQLException, JSONException {
		JSONObject inputJsonObj = new JSONObject(data);
		System.out.println("hi0");
		String username = inputJsonObj.getString("username");
		String password = inputJsonObj.getString("password");
		
		GenericUser gu = GenericUserDaoImpl.searchUser(username);
		if(gu == null)	{ return Response.serverError().status(Status.EXPECTATION_FAILED).build(); }
		System.out.println("hi");
		if(password.equals(gu.getPassword())) {
			String TypeOfUser = null;
			
			if (gu.get_is_Bank_User()) {
				TypeOfUser = "Bank";
			} else {
				TypeOfUser = "Customer";
			}
			
			Session s = new Session(gu.getUsername(), TypeOfUser);
			HttpSession hs = request.getSession();//CREATE A SESSION FOR THE USER.
			hs.setAttribute("session", s);
			return Response.ok("Logged in Successfully").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();  // Here we can redirect to the landing page
		}		
		return Response.serverError().status(Status.EXPECTATION_FAILED).build();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/getUserInfo/")
	public String checkToken(@Context HttpServletRequest request) throws SQLException, JSONException{
		Session s = SessionUtility.sessionValidation(request);
		if(s != null) {
			String obj = "{\"username\": \"" + s.getUserId() + "\", \"userType\": \"" + s.userType + "\"}";
			return obj;
		}
		return "no session";
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
		String name = inputJsonObj.getString("name");
		String email = inputJsonObj.getString("email");
		String password = inputJsonObj.getString("password");
		
		Customer c = new Customer(name,email);
		GenericUser gu = new GenericUser(email,password,false, false, false);
		System.out.println(c);
		
		if(CustomerDaoImpl.insertIntoCustomers(gu,c)) {
			return Response.ok("SignupSuccess").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();
		}
		return Response.serverError().status(Status.EXPECTATION_FAILED).build();
	}
	
	
	@GET
	@Path("/fetchCustomerDetails/{uname}")
	@Produces(MediaType.APPLICATION_JSON)
	public String fetchCustomerDetails(@QueryParam("uname") String Uname ,@Context HttpServletRequest request) throws SQLException, JsonGenerationException, JsonMappingException, IOException{
		Customer c = CustomerDaoImpl.searchCustomer(Uname);
		if(c == null)	{ return null; }
		return c.convertObjectToJSON();
	}
	
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/signupUser/")
	public Response signupUser(String data, @Context HttpServletRequest request) throws SQLException, JSONException{
		JSONObject inputJsonObj = new JSONObject(data);
		String username= inputJsonObj.getString("CorpID");
		String password = inputJsonObj.getString("Password");
		String Fullname = inputJsonObj.getString("Fullname");
		String address = inputJsonObj.getString("address");
		String p_group = inputJsonObj.getString("privgrp");
		BankUser b = new BankUser(username,Fullname,address,p_group);
		GenericUser gu = new GenericUser(username,password,false, false, true);
		System.out.println(b);
		
		if(BankUserDaoImpl.insertIntoBankUser(gu,b)) {
			return Response.ok("SignupSuccess").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();
		}
		return Response.serverError().status(Status.EXPECTATION_FAILED).build();
	}
	
	
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/updateDetails/")

	public Response addDetails(String data, @Context HttpServletRequest request) throws SQLException, JSONException {
		JSONObject JsonObj = new JSONObject(data);
		System.out.println("hi");
		//String username = JsonObj.getString("Username");
		String swift = JsonObj.getString("Swift");
		int accnumber = JsonObj.getInt("AccNumber");
		int contnumber = JsonObj.getInt("ContNumber");
		String postallocation = JsonObj.getString("PostalLocation");
		String postalcity = JsonObj.getString("PostalCity");
		String postalstate = JsonObj.getString("PostalState");
		String factorylocation = JsonObj.getString("FactoryLocation");
		String factorycity = JsonObj.getString("FactoryCity");
		String factorystate = JsonObj.getString("FactoryState");
		String department = JsonObj.getString("Department");
		String username = "neha";
		return Response.ok("Details updated Successfully").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();
		/*
		AdditionalDetails ad = new AdditionalDetails(username,swift, accnumber, contnumber, postallocation, factorylocation, postalcity, factorycity, postalstate, factorystate, department);
		if (AdditionalDetailsDao.insertIntoAdditionalDetails(ad))
		{
			return Response.ok("Details updated Successfully").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();
		}
		return Response.serverError().status(Status.EXPECTATION_FAILED).build();*/
	}




	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/updateProducts/")
	public Response addproducts(String data, @Context HttpServletRequest request) throws SQLException, JSONException {
		JSONObject JsonObj = new JSONObject(data);
		JSONArray productCategories = JsonObj.getJSONArray("UserProductsCategories");
		JSONArray products = JsonObj.getJSONArray("UserProducts");
		String username = "neha";
		String[] prod = new String[products.length()];
		
		System.out.println(products);
		System.out.println(productCategories);
		
		
		return null;
	}
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/forgotPassword")
	public Response forgotPassword(String data, @Context HttpServletRequest request) throws SQLException, JSONException {
		
		System.out.println("entered into forgotPassword service");
				
		JSONObject inputJsonObj = new JSONObject(data);
		String customerEmail = inputJsonObj.getString("email");		
		String subjectToSend="Password change request";
		Random rand = new Random();			 
		String randomString   = Integer.toString(rand.nextInt(5000));
		System.out.println("the random string generated is"+randomString);
		String messageToSend = "Hi!! To reset the password kindly click the link. \n" + "\n<a href='http://localhost:8080/MyRestDemo/pages/ChangePassword.htm?token="+randomString+"'>Reset password</a>";
		
		

		System.out.print(customerEmail + " this is the email where i have to send the message");
		MyMailClass.sendMail(customerEmail ,messageToSend,subjectToSend);
		
		/*try {
			//URL myURL = new URL("http://localhost:8080/MyRestDemo/pages/ChangePassword.htm");
			//String messageToSend="http://localhost:8080/MyRestDemo/pages/ChangePassword.htm";
			//messageBodyPart.setText(html, "UTF-8", "html");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("url not formed properly!!");
			e.printStackTrace();
		}*/
		
		//String customerEmail =http://localhost:8080/MyRestDemo/pages/ChangePassword.htm
		//GenericUser gu = GenericUserDaoImpl.searchUser(Uri);//check in database using email id
		//if(gu == null)	{ return Response.serverError().status(Status.EXPECTATION_FAILED).build(); }
		
		//construct url		
		
			
		
				
	    return Response.ok("Logged in Successfully").header("Access-Control-Allow-Origin", "*").status(Status.OK).build();  // Here we can redirect to the landing page
				
	
		
	}
			
}
