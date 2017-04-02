package com.example.resource;

import javax.json.stream.JsonGenerator;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api/*")
public class JsonResourceConfig extends ResourceConfig {
	
    public JsonResourceConfig() {
        register(BookResource.class);
        
        // 设置格式打印
        property(JsonGenerator.PRETTY_PRINTING, true);  
    }
}
