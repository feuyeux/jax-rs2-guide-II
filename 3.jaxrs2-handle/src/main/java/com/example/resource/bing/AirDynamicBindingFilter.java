package com.example.resource.bing;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import org.apache.log4j.Logger;

public class AirDynamicBindingFilter implements ContainerRequestFilter {
  private static final Logger LOGGER = Logger.getLogger(AirDynamicBindingFilter.class);

  public AirDynamicBindingFilter() {
    LOGGER.info("Air-Dynamic-Binding-Filter initialized");
  }

  @Override
  public void filter(final ContainerRequestContext requestContext) throws IOException {
    AirDynamicBindingFilter.LOGGER.debug("Air-Dynamic-Binding-Filter invoked");
  }
}
