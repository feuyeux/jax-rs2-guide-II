package com.example.resource.filter;

import org.apache.log4j.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@PreMatching
@Provider
public class AirContainerRequestPreFilter implements ContainerRequestFilter {
    private static final Logger LOGGER = Logger.getLogger(AirContainerRequestPreFilter.class);

    public AirContainerRequestPreFilter() {
        LOGGER.info("Air-Container-Request-Pre-Filter initialized");
    }

    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {
        AirContainerRequestPreFilter.LOGGER.debug("Air-Container-Request-Pre-Filter invoked");
    }
}
