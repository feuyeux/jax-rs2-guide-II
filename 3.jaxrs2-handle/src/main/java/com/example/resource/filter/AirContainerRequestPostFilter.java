package com.example.resource.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider
public class AirContainerRequestPostFilter implements ContainerRequestFilter {
    private static final Logger LOGGER = Logger.getLogger(AirContainerRequestPostFilter.class);

    public AirContainerRequestPostFilter() {
        LOGGER.info("Air-Container-Request-Post-Filter initialized");
    }

    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {
        AirContainerRequestPostFilter.LOGGER.debug("Air-Container-Request-Post-Filter invoked");
    }
}
