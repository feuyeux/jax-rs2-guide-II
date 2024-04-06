package com.example;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Component
@Path("/")
public class MyResource {
  @Autowired private DiscoveryClient discovery;

  @Value("${spring.application.name:bootEtcd}")
  private String appName;

  @Path("hi2")
  @GET
  public String hi2() {
    List<ServiceInstance> serviceInstances = getServices();
    StringBuilder result = new StringBuilder("        ");
    for (ServiceInstance s : serviceInstances) {
      result.append("server ").append(s.getHost()).append(":").append(s.getPort()).append(";");
    }
    return result.toString();
  }

  @Path("all")
  @GET
  @Produces("application/json")
  public List<ServiceInstance> getServices() {
    return discovery.getInstances(appName);
  }

  @Path("health")
  @GET
  public String health() {
    return "OK";
  }
}
