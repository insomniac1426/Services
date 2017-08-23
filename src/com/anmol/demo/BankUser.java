package com.anmol.demo;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class BankUser{
	
	String corpid;
	String password;
	String name;
    String address;
	String p_group;
	//ctor
	public BankUser(String corpid, String password, String name,String address,String p_group) {
		super();
		this.corpid = corpid;
		this.name = name;
		this.password = password;
		this.address=address;
		this.p_group=p_group;
		
	}
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getcorpid() {
		return corpid;
	}
	public void setcorpid(String corpid) {
		this.corpid = corpid;
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
		return "BankUser [corpid=" + corpid   + ", password=" + password + " , name=" + name + " , address="+ address + ", p_group=" + p_group + "]";
	}




	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

//converting object to json string.
	public String convertObjectToJSON() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(this);
		return json;
	}	
	
	
	
	
	
}
