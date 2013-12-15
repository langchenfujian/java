package com.test.jackson.website;

import java.io.IOException;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.demo.util.Util;

public class FooTest {
	@Test
	public void testStream() throws JsonParseException, IOException{
		ObjectMapper om = new ObjectMapper();
		JsonFactory jf = new JsonFactory();
		JsonParser jp = jf.createJsonParser(Util.getInputStreamByFileClassPath("website/foo.json"));
		jp.nextToken();
		while(jp.nextToken() == JsonToken.START_OBJECT){
			Foo foo = om.readValue(jp, Foo.class);
			System.out.println(foo);
		}
	}
}
