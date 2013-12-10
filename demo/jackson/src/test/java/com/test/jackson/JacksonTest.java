package com.test.jackson;

import java.io.IOException;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;

public class JacksonTest {
	private JsonGenerator jsonGenerator;
	private ObjectMapper objectMapper;
	private AccountBean bean;
	
	@Before
	public void setup() throws IOException{
		bean = new AccountBean();
		bean.setAddress("FuQing, China");
		bean.setId(1);
		bean.setBirthday(new Birthday("1/15/1987"));
		bean.setEmail("lang.chen@hp.com");
		
		objectMapper = new ObjectMapper();
		jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
	}
	
	@After
	public void teardown() throws IOException{
		if(jsonGenerator != null){
			jsonGenerator.flush();
			if(!jsonGenerator.isClosed()){
				jsonGenerator.close();
			}
		}
	}
}
