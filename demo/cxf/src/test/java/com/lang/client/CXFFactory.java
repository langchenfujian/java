package com.lang.client;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

public class CXFFactory {
	private String baseURI;
	
	public CXFFactory(String baseURI) {
		super();
		this.baseURI = baseURI;
	}

	public <T> T getServiceProxy(Class<T> T){
		return JAXRSClientFactory.create(baseURI, T);
	}
}
