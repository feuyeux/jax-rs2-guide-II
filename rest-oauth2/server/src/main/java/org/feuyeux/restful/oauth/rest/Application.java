package org.feuyeux.restful.oauth.rest;

import org.glassfish.jersey.server.ResourceConfig;

public class Application extends ResourceConfig {

    public Application() {
        register(JerseyResource.class);
        register(ConferenceService.class);
    }
}
