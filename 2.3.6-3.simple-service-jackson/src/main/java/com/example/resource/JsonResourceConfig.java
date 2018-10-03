package com.example.resource;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api/*")
public class JsonResourceConfig extends ResourceConfig {
    public JsonResourceConfig() {
        register(BookResource.class);
        register(JacksonFeature.class);
        register(JsonContextProvider.class);
    }
}
