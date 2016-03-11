package org.feuyeux.restful.oauth2.server.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class JerseyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {
        return "hello Jersey";
    }

}