package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.List;

@Component
@Path("/")
public class MyResource {
    @Context
    HttpServletRequest request;
    @Autowired
    private DiscoveryClient discovery;
    @Autowired
    private DogService dogService;
    @Autowired
    private EagleService eagleService;

    @Value("${spring.application.name:bootZKKafka}")
    private String appName;

    @PostConstruct
    public void init() {
        eagleService.start();
    }

    @Path("kk")
    @GET
    @Produces("application/json")
    public List<String> kk() {
        return EagleService.getTemp();
    }

    @Path("hi")
    @GET
    public String hi() {
        ServiceInstance serviceInstance = discovery.getLocalServiceInstance();
        String result = serviceInstance.getHost() + ":" + serviceInstance.getPort();
        if (request != null) {
            dogService.process(request.getRemoteAddr());
        }
        return result;
    }

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
}