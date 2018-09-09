package com.example.resource;

import org.glassfish.jersey.server.ResourceConfig;

import javax.json.stream.JsonGenerator;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api/*")
public class JsonResourceConfig extends ResourceConfig {
    public JsonResourceConfig() {
        register(BookResource.class);
        property(JsonGenerator.PRETTY_PRINTING, true);
    }
}
