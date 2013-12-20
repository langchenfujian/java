package com.lang.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.lang.entity.MapBean;
import com.lang.entity.User;
import com.lang.entity.Users;

@Path(value="/sample")
public interface RESTSample {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String doGet();
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/request/{param}")
	public String doRequest(@PathParam("param") String param, 
			@Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);
	
	@GET
	@Path("/bean/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getBean(@PathParam("id") int id);
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/list")
	public Users getList();
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/map")
	public MapBean getMap();
	
	@POST
	@Path("/postData")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public User postData(User user) throws IOException;
	
	@PUT
	@Path("/putData/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public User putData(@PathParam("id") int id, User user);
	
	@DELETE
	@Path("/removeData/{id}")
	public void deleteData(@PathParam("id") int id);
}
