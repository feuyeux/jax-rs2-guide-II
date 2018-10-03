package com.example.client.resource;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/client/*")
public class AirResourceConfig extends ResourceConfig {

    public AirResourceConfig() {
        packages("com.example.client");
    }
}