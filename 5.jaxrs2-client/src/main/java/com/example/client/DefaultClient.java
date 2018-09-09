package com.example.client;

import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.ClientBuilder;

public class DefaultClient extends Jaxrs2Client {
    public DefaultClient() {
        buildClient();
    }

    void buildClient0() {
        client = ClientBuilder.newClient();
    }

    void buildClient() {
        final ClientConfig clientConfig = new ClientConfig();
        clientConfig.property("TestKey", "TestValue");
        client = ClientBuilder.newClient(clientConfig);
        client.property("TestKey2", "TestValue2");
        checkConfig();
    }
}
