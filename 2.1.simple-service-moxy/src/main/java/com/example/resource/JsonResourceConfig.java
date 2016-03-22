package com.example.resource;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api/*")
public class JsonResourceConfig extends ResourceConfig {
    public JsonResourceConfig() {
        register(BookResource.class);
        /*
        property(org.glassfish.jersey.CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
        */
    }
}
