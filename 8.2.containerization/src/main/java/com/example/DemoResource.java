package com.example;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("hi")
@EnableCaching
@CacheConfig(cacheNames = "saying")
public class DemoResource {
    @GET
    @Produces("application/json")
    public List<String> hi() {
        List<String> result = new ArrayList<>();
        result.add("hello spring boot");
        result.add("hello docker");
        return result;
    }

    @GET
    @Path("{v}")
    @Cacheable()
    public String hi2(@PathParam("v") String v) {
        return v + "-" + System.nanoTime();
    }
}