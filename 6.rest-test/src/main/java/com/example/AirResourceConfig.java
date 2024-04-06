package com.example;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * AirResourceConfig class.
 *
 * @author hanl
 * @version $Id: $Id
 */
@ApplicationPath("/webapi/*")
public class AirResourceConfig extends ResourceConfig {
  /** Constructor for AirResourceConfig. */
  public AirResourceConfig() {
    packages("com.example.resource");
    /** register(BookResource.class);* */
  }
}
