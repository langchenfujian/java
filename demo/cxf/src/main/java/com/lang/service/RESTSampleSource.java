package com.lang.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.lang.entity.MapBean;
import com.lang.entity.User;
import com.lang.entity.Users;
public class RESTSampleSource implements RESTSample {
	@Context
	private UriInfo uriInfo;
	@Context
	private Request request;
	
	@Override
	public String doGet() {
		return "This is rest get request";
	}

	@Override
	public String doRequest(String param,HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {
		System.out.println(servletRequest);
		System.out.println(servletResponse);
		System.out.println(servletRequest.getParameter("param"));
		System.out.println(param);
		System.out.println(servletRequest.getContentType());
		System.out.println(servletResponse.getCharacterEncoding());
		System.out.println(servletResponse.getContentType());
		return "success";
	}
	
	@Override
	public User getBean(int id) {
		System.out.println("####getBean#####");
        System.out.println("id:" + id);
        System.out.println("Method:" + request.getMethod());
        System.out.println("uri:" + uriInfo.getPath());
        System.out.println(uriInfo.getPathParameters());
        
        User user = new User();
        user.setId(id);
        user.setName("Jing");
        return user;
	}

	@Override
	public Users getList() {
		System.out.println("####getList#####");
        System.out.println("Method:" + request.getMethod());
        System.out.println("uri:" + uriInfo.getPath());
        System.out.println(uriInfo.getPathParameters());
        
        List<User> list = new ArrayList<User>();
        User user = null;
        for (int i = 0; i < 4;i ++) {
            user = new User();
            user.setId(i);
            user.setName("Qing - " + i);
            list.add(user);
        }
        Users users = new Users();
        users.setUsers(list);
        return users;
	}

	@Override
	public MapBean getMap() {
		// System.out.println("####getMap#####");
        System.out.println("Method:" + request.getMethod());
        System.out.println("uri:" + uriInfo.getPath());
        System.out.println(uriInfo.getPathParameters());
        
        Map<String, User> map = new HashMap<String, User>();
        User user = null;
        for (int i = 0; i < 4;i ++) {
            user = new User();
            user.setId(i);
            user.setName("Wen - " + i);
            map.put("key - " + i, user);
        }
        MapBean bean = new MapBean();
        bean.setMap(map);
        return bean;
	}

	@Override
	public User postData(User user) throws IOException {
		System.out.println(user);
		user.setId(1233);
        //user.setName("Yun##12321321");
        return user;
	}

	@Override
	public User putData(int id, User user) {
		System.out.println("#####putData#####");
        System.out.println(user);
        user.setId(id);
        user.setAddress("Shang");
        user.setMail("jing.leng@126.com");
        user.setName("Jing");
        System.out.println(user);
        return user;
	}

	@Override
	public void deleteData(int id) {
		System.out.println("#######deleteData#######" + id);
	}

}
