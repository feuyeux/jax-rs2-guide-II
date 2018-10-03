package com.example;

import javax.ws.rs.ApplicationPath;

import com.example.resource.BookResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 * <p>AirResourceConfig class.</p>
 *
 * @author hanl
 * @version $Id: $Id
 */
@ApplicationPath("/webapi/*")
public class AirResourceConfig extends ResourceConfig {
    /**
     * <p>Constructor for AirResourceConfig.</p>
     */
    public AirResourceConfig() {
        super(RolesAllowedDynamicFeature.class, BookResource.class);
    }
}
