package com.example;

import com.example.resource.BookResource;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

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
    super(RolesAllowedDynamicFeature.class, BookResource.class);
  }
}
