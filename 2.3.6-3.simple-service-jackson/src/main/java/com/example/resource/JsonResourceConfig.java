package com.example.resource;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api/*")
public class JsonResourceConfig extends ResourceConfig {
    public JsonResourceConfig() {
        register(BookResource.class);
        
        // 手动开启json的转换功能   zzkk
        register(JacksonFeature.class);
        register(JsonContextProvider.class);
    }
}
