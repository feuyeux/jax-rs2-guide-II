package com.example;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class AirApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(MyResource.class);
		return classes;
	}
}
