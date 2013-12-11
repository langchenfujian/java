package com.test.jackson.website;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.junit.Test;

import com.demo.util.Util;
import com.test.jackson.website.User.Gender;
import com.test.jackson.website.User.Name;

public class UserTest {
	@Test
	public void testJsonToEntity() throws JsonParseException, JsonMappingException,
			IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(
				Util.getInputStreamByFileClassPath("website/user.json"),
				User.class);
		System.out.println(user);
	}

	@Test
	public void testMapToJson() throws JsonGenerationException, JsonMappingException,
			IOException {
		Map<String, Object> userData = new HashMap<String, Object>();
		Map<String, String> nameStruct = new HashMap<String, String>();
		nameStruct.put("first", "Joe");
		nameStruct.put("last", "Sixpack");
		userData.put("name", nameStruct);
		userData.put("gender", "MALE");
		userData.put("verified", Boolean.FALSE);
		userData.put("userImage", "Rm9vYmFyIQ==");

		ObjectMapper objectMapper = new ObjectMapper();

		// objectMapper.writeValue(System.out, userData);
		String res = objectMapper.writeValueAsString(userData);
		System.out.println(res);
	}

	@Test
	public void testTreeOperation() throws JsonProcessingException, IOException{
		ObjectMapper m = new ObjectMapper();
		// can either use mapper.readTree(source), or mapper.readValue(source, JsonNode.class);
		JsonNode rootNode = m.readTree(Util.getInputStreamByFileClassPath("website/user.json"));
		// ensure that "last name" isn't "Xmler"; if is, change to "Jsoner"
		JsonNode nameNode = rootNode.path("name");
		String lastName = nameNode.path("last").getTextValue();
		if ("Sixpack".equalsIgnoreCase(lastName)) {
		  ((ObjectNode)nameNode).put("last", "Chen");
		}
		// and write it out:
		System.out.println(m.writeValueAsString(rootNode));
	}
	@Test
	public void testCreateNewTree() throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper m = new ObjectMapper();
		ObjectNode userOb = m.createObjectNode();
		ObjectNode nameOb = userOb.putObject("name");
		nameOb.put("first", "Joe");
		nameOb.put("last", "Sixpack");
		userOb.put("gender", User.Gender.MALE.toString());
		userOb.put("verified", false);
		byte[] imageData = getImageData(); // or wherever it comes from
		userOb.put("userImage", imageData);
		System.out.println(m.writeValueAsString(userOb));
	}

	private byte[] getImageData() {
		return "asdffdsa".getBytes();
	}
	@Test
	public void testStreamAPI() throws JsonGenerationException, IOException{
		JsonFactory f = new JsonFactory();
		String userHomePath = System.getProperty("user.home");
		JsonGenerator g = f.createJsonGenerator(new File(userHomePath+"/EclipseTest/"+"test.json"), JsonEncoding.UTF8);
		g.writeStartObject();g.writeObjectFieldStart("name");
		g.writeStringField("first", "Joe");
		g.writeStringField("last", "Sixpack");
		g.writeEndObject(); // for field 'name'
		g.writeStringField("gender", Gender.MALE.toString());
		g.writeBooleanField("verified", false);
		g.writeFieldName("userImage"); // no 'writeBinaryField' (yet?)
		byte[] binaryData = this.getImageData();
		g.writeBinary(binaryData);
		g.writeEndObject();
		g.close(); // important: will force flushing of output, close underlying output stream
	}
	@Test
	public void testJsonParser() throws IOException{
		JsonFactory f = new JsonFactory();
		String userHomePath = System.getProperty("user.home");
		JsonParser jp = f.createJsonParser(new File(userHomePath+"/EclipseTest/"+"test.json"));
		User user = new User();
		jp.nextToken(); // will return JsonToken.START_OBJECT (verify?)
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			String fieldname = jp.getCurrentName();  
			jp.nextToken(); // move to value, or START_OBJECT/START_ARRAY  
			if ("name".equals(fieldname)) { // contains an object    
				Name name = new Name();    
				while (jp.nextToken() != JsonToken.END_OBJECT) {      
					String namefield = jp.getCurrentName();
					jp.nextToken(); // move to value      
					if ("first".equals(namefield)) {        
						name.setFirst(jp.getText());      
					} else if ("last".equals(namefield)) {        
						name.setLast(jp.getText());      
					} else {        
						throw new IllegalStateException("Unrecognized field '"+fieldname+"'!");      
					}    
				}    
				user.setName(name);  
			} else if ("gender".equals(fieldname)) {    
				user.setGender(User.Gender.valueOf(jp.getText()));  
			} else if ("verified".equals(fieldname)) {    
				user.setVerified(jp.getCurrentToken() == JsonToken.VALUE_TRUE);  
			} else if ("userImage".equals(fieldname)) {    
				user.setUserImage(jp.getBinaryValue());  
			} else {    
				throw new IllegalStateException("Unrecognized field '"+fieldname+"'!");  
			}
		}
		jp.close(); // ensure resources get cleaned up timely and properly
		System.out.println(user);
	}
}
