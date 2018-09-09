package com.example;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class AirApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(MyResource.class);
		return classes;
	}
}
