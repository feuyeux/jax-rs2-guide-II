package com.example.client;

import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.grizzly.connector.GrizzlyConnectorProvider;

public class GrizzlyClient extends Jaxrs2Client {
    public GrizzlyClient() {
        buildClient();
    }

    void buildClient() {
        final ClientConfig clientConfig = new ClientConfig();
        clientConfig.property("TestKey", "TestValue");

        clientConfig.connectorProvider(new GrizzlyConnectorProvider());
        client = ClientBuilder.newClient(clientConfig);
        checkConfig();
    }

}
