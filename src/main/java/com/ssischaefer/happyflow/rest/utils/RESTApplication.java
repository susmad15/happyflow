package com.ssischaefer.happyflow.rest.utils;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.ssischaefer.happyflow.rest.resource.ContainerResource;
import com.ssischaefer.happyflow.rest.resource.PackageResource;
import com.ssischaefer.happyflow.rest.resource.ProductResource;

public class RESTApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	
	// Load singletons for REST server
	public RESTApplication() {
		singletons.add(new ContainerResource());
		singletons.add(new PackageResource());
		singletons.add(new ProductResource());
		System.out.println("[RESTApplication] Singletons loaded: " + singletons.size());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
