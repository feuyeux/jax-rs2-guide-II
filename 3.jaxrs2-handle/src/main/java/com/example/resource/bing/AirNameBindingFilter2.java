package com.example.resource.bing;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider
@AirLog
@Priority(Priorities.USER + 1)
public class AirNameBindingFilter2 implements ContainerRequestFilter, ContainerResponseFilter {
    private static final Logger LOGGER = Logger.getLogger(AirNameBindingFilter2.class);

    public AirNameBindingFilter2() {
        LOGGER.info("Air-NameBinding-Filter2 Priority+1 initialized");
    }

    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {
        LOGGER.debug("Air-NameBinding-ContainerRequestFilter2  Priority+1 invoked");
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
        throws IOException {
        LOGGER.debug("Air-NameBinding-ContainerResponseFilter2 Priority+1 invoked");
    }
}
