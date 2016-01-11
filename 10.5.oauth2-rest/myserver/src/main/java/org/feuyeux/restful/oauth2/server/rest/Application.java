package org.feuyeux.restful.oauth2.server.rest;

import org.glassfish.jersey.server.ResourceConfig;

public class Application extends ResourceConfig {

    public Application() {
        register(JerseyResource.class);
        register(RestService.class);
    }
}
