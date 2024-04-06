package com.example;

import com.example.resource.BookResource;
import com.example.resource.bing.AirNameBindingFilter;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

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
