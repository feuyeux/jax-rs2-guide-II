package com.example;

import com.example.resource.BookResource;
import com.example.resource.bing.AirNameBindingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/aop/*")
public class AirAopConfig extends ResourceConfig {

    public AirAopConfig() {
        register(BookResource.class);
        register(AirNameBindingFilter.class);
    }

    public AirAopConfig(Class<BookResource> registerClass) {
        super(registerClass);
        register(AirNameBindingFilter.class);
    }
}