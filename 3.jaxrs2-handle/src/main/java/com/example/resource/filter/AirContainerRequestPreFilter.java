package com.example.resource.filter;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

@PreMatching
@Provider
public class AirContainerRequestPreFilter implements ContainerRequestFilter {
  public AirContainerRequestPreFilter() {
    // LOGGER.info("Air-Container-Request-Pre-Filter initialized");
  }

  @Override
  public void filter(final ContainerRequestContext requestContext) throws IOException {
    // AirContainerRequestPreFilter.LOGGER.debug("Air-Container-Request-Pre-Filter invoked");
  }
}
