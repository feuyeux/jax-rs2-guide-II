package com.example;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/webapi/*")
public class AirResourceConfig extends ResourceConfig {
	public AirResourceConfig() {
		packages("com.example");
	}
}