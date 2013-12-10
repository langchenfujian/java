package com.test.jackson.website;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.demo.util.Util;

public class UserTest {
	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(Util.getInputStreamByFileClassPath("website/user.json"), User.class);
		System.out.println(user);
	}
	@Test
	public void testMap() throws JsonGenerationException, JsonMappingException, IOException{
		Map<String,Object> userData = new HashMap<String,Object>();
		Map<String,String> nameStruct = new HashMap<String,String>();
		nameStruct.put("first", "Joe");
		nameStruct.put("last", "Sixpack");
		userData.put("name", nameStruct);
		userData.put("gender", "MALE");
		userData.put("verified", Boolean.FALSE);
		userData.put("userImage", "Rm9vYmFyIQ==");
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		//objectMapper.writeValue(System.out, userData);
		String res = objectMapper.writeValueAsString(userData);
		System.out.println(res);
	}
}
