package com.examples.sse;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

public class ClientUtil {
    private static final int MAX_LISTENERS = 5;
    private static final int MAX_ITEMS = 10;

    public static ClientConfig buildApacheConfig(ClientConfig config) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(MAX_LISTENERS * MAX_ITEMS);
        cm.setDefaultMaxPerRoute(MAX_LISTENERS * MAX_ITEMS);
        config.property(ApacheClientProperties.CONNECTION_MANAGER, cm);
        config.connectorProvider(new ApacheConnectorProvider());
        return config;
    }
}
