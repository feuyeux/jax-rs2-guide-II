package com.examples.sse;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.sse.SseFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * <p>AirResourceConfig class.</p>
 *
 * @author hanl
 * @version $Id: $Id
 */
@ApplicationPath("/event/*")
public class AirResourceConfig extends ResourceConfig {
    /**
     * <p>Constructor for AirResourceConfig.</p>
     */
    public AirResourceConfig() {
        super(SseFeature.class, AirSsePubSubResource.class, AirSseBroadcastResource.class);
    }
}