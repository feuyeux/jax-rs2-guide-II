package com.example.resource;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jettison.JettisonFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api/*")
public class JsonResourceConfig extends ResourceConfig {
    public JsonResourceConfig() {
        register(BookResource.class);
        register(JettisonFeature.class);
        register(JsonContextResolver.class);
    }
}
