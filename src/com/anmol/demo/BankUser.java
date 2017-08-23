package com.anmol.demo;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class BankUser{
	
	
	String Fullname;
    String address;
	String p_group;
	//ctor
	public BankUser( String Fullname,String address,String p_group) {
		super();
		
		this.Fullname = Fullname;
		this.address=address;
		this.p_group=p_group;
		
	}
	 
	public String getFullname() {
		return Fullname;
	}
	public void setName(String Fullname) {
		this.Fullname = Fullname;
	}
	
	public String getaddress() {
		return address;
	}
	public void setaddress(String address) {
		this.address = address;
	}
	
	public String getp_group() {
		return p_group;
	}
	public void setp_group(String p_group) {
		this.p_group = p_group;
	}
	@Override
	public String toString() {
		return "BankUser [  Fullname=" + Fullname + " , address="+ address + ", p_group=" + p_group + "]";
	}


//converting object to json string.
	public String convertObjectToJSON() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(this);
		return json;
	}	
	
	
	
	
	
}
