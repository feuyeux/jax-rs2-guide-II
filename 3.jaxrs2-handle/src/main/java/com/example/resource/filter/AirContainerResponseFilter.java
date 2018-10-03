package com.example.resource.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider
public class AirContainerResponseFilter implements ContainerResponseFilter {
    private static final Logger LOGGER = Logger.getLogger(AirContainerResponseFilter.class);

    public AirContainerResponseFilter() {
        LOGGER.info("Air-Container-Response-Filter initialized");
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
        throws IOException {
        LOGGER.info("Air-Container-Response-Filter invoked");

    }
}
