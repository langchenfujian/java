package com.lang.client;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lang.service.RESTSample;

public class RestServiceClient2 {
	CXFFactory cxfFactory;
	@Before
	public void init(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-client2.xml");
		cxfFactory = ctx.getBean("cxfFactory", CXFFactory.class);
	}
	@Test
	public void test(){
		RESTSample restSample = cxfFactory.getServiceProxy(RESTSample.class);
		System.out.println(restSample.getBean(111));
		System.out.println(restSample.getMap());
	}
}
