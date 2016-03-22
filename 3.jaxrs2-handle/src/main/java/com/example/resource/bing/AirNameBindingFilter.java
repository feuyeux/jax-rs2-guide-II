package com.example.resource.bing;

import org.apache.log4j.Logger;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

@AirLog
@Priority(Priorities.USER)
public class AirNameBindingFilter implements ContainerRequestFilter, ContainerResponseFilter {
    private static final Logger LOGGER = Logger.getLogger(AirNameBindingFilter.class);

    public AirNameBindingFilter() {
        LOGGER.info("Air-NameBinding-Filter initialized");
    }

    @Override
    public void filter(final ContainerRequestContext containerRequest) throws IOException {
        LOGGER.debug("Air-NameBinding-ContainerRequestFilter invoked:" + containerRequest.getMethod());
        LOGGER.debug(containerRequest.getUriInfo().getRequestUri());
    }

    @Override
    public void filter(ContainerRequestContext containerRequest, ContainerResponseContext responseContext) throws IOException {
        LOGGER.debug("Air-NameBinding-ContainerResponseFilter invoked:" + containerRequest.getMethod());
        LOGGER.debug("status=" + responseContext.getStatus());
    }
}
