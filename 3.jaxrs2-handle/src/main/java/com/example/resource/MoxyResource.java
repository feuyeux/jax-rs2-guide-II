package com.example.resource;


import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("json")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MoxyResource {
    private static final Logger LOGGER = Logger.getLogger(MoxyResource.class);

    @GET
    public void writing() {
        LOGGER.debug("MOXy writing...");
    }
}
