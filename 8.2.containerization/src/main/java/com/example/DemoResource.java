package com.example;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("hi")
public class DemoResource {
    @Autowired
    private CacheComponent cacheComponent;

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
    public String hi2(@PathParam("v") String v) {
        return cacheComponent.hi(v);
    }
}