package com.ssischaefer.happyflow.rest;

import java.net.URI;
import java.net.URISyntaxException;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.ssischaefer.happyflow.rest.utils.RESTApplication;
import com.sun.net.httpserver.HttpServer;

public class RESTServer {

	private final static String URL = "http://localhost:1500/rest";
	private static HttpServer server;

	
	// Connect to REST server
	public static void connect() {
		URI endpoint;
		
		try {
			endpoint = new URI(URL);
			ResourceConfig rc = ResourceConfig.forApplicationClass(RESTApplication.class);
			server = JdkHttpServerFactory.createHttpServer(endpoint, rc);
			System.out.println("[RESTServer] The server was created. " + URL);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	
	// Disconnect from REST server
	public static void disconnect() {
		server.stop(0);
		
		System.out.println("[RESTServer] The server was deleted. " + URL);
	}

}
