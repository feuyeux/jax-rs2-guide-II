package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("hi")
public class DemoResource {
    @GET
    @Produces("application/json")
    public List<String> hi() {
        List<String> result = new ArrayList<>();
        result.add("hello spring boot");
        result.add("hello micro services");
        return result;
    }
}
