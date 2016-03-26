package com.example;

import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class AirApplication extends ResourceConfig {

    public AirApplication() {
        property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);
        property(CommonProperties.JSON_PROCESSING_FEATURE_DISABLE, true);
        property(CommonProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
        property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
        property(CommonProperties.OUTBOUND_CONTENT_LENGTH_BUFFER, 20480);

        property(ServerProperties.WADL_FEATURE_DISABLE, true);
        packages("com.example.resource");
    }
}
