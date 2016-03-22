package com.example.resource;


import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("test")
public class ByteArrayResource {
    private static final Logger LOGGER = Logger.getLogger(ByteArrayResource.class);

    @GET
    public byte[] reading() {
        final String s = "Hello";
        return s.getBytes();
    }
}
