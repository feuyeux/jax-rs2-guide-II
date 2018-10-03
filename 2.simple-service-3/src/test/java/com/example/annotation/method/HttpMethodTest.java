package com.example.annotation.method;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.grizzly.connector.GrizzlyConnectorProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

public class HttpMethodTest extends JerseyTest {
    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(EBookResourceImpl.class);
    }

    @Override
    protected void configureClient(ClientConfig clientConfig) {
        //2.5- :clientConfig.connector(new GrizzlyConnector(clientConfig));
        clientConfig.connectorProvider(new GrizzlyConnectorProvider());
        super.configureClient(clientConfig);
    }

    @Test
    public void testWebDav() {
        final Response response = target("book").request().method("MOVE");
        Boolean result = response.readEntity(Boolean.class);
        Assert.assertEquals(Boolean.TRUE, result);
    }
}
