package com.example.resource;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api/*")
public class JsonResourceConfig extends ResourceConfig {
    public JsonResourceConfig() {
        register(BookResource.class);
        //property(org.glassfish.jersey.CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
    }
}
