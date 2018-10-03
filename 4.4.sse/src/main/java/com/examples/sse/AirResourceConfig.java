package com.examples.sse;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.sse.SseFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/event/*")
public class AirResourceConfig extends ResourceConfig {

    public AirResourceConfig() {
        super(
            SseFeature.class,
            AirSsePubSubResource.class,
            AirSseBroadcastResource.class
        );
    }
}