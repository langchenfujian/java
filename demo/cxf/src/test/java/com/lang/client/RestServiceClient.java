package com.lang.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lang.entity.MapBean;
import com.lang.entity.User;
import com.lang.entity.Users;

public class RestServiceClient {
	private static WebClient client;
	
	@Before
	public void init(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-client.xml");
		client = ctx.getBean("webClient", WebClient.class);
	}
	
	@Test
	public void testGet(){
		System.out.println(client.path("sample").accept(MediaType.TEXT_PLAIN).get(String.class));
	}
	
	@Test
	public void testRequest(){
		System.out.println(client.path("sample/request/2342134").accept(MediaType.TEXT_PLAIN).get(String.class));
	}
	
	@Test
	public void testBean(){
		User user = client.path("sample/bean/{id}", 222).accept(MediaType.APPLICATION_JSON).get(User.class);
		System.out.println(user);
	}
	
	@Test
	public void testList(){
		System.out.println(client.path("sample/list").accept(MediaType.APPLICATION_XML).get(Users.class).getUsers());
	}
	
	@Test
	public void testMap(){
		System.out.println(client.path("sample/map").accept(MediaType.APPLICATION_XML).get(MapBean.class).getMap());
	}
	
	@Test
	public void testDelete(){
		client.path("sample/removeData/234").delete();
	}
	
	@Test
	public void testPostData(){
		User user = new User();
		user.setAddress("asdf");
		user.setMail("lang.chen@hp.com");
		user.setName("lang");
		System.out.println(client.path("sample/postData").accept(MediaType.APPLICATION_XML).post(user, User.class));
	}
	
	@Test
	public void testPutData() throws IOException{
		User user = new User();
		InputStream is = (InputStream)client.path("sample/putData/{id}",2345).accept(MediaType.APPLICATION_XML).put(user).getEntity();
		BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while((line = bufferedInputStream.readLine())!=null){
			System.out.println(line);
		}
		System.out.println();
	}
}
