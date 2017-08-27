package com.anmol.demo;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class UserProducts {
	String username;
	String[] electronics={"mobile", "camera"};
	String[] appliances={"refrigerator", "microwave"};
	String[] clothing = {"shirts", "pants"};
	String[][] allProducts = {electronics, appliances, clothing};
	String[] userProducts = new String[6];
	public String[] getUserProducts() {
		return userProducts;
	}
	public void setUserProducts(String[] userProducts) {
		this.userProducts = userProducts;
	}
	
	public String convertObjectToJSON() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(this);
		return json;
}
}