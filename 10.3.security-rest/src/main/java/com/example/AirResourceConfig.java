package com.example;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.example.resource.BookResource;

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
