package com.example.client.common;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.example.client.Jaxrs2Client;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;

public class BasicTest {
    HttpServer server;

    @Before
    public void begin() {
        final ResourceConfig resourceConfig = new ResourceConfig();
        final ResourceConfig rc = resourceConfig.packages("com.example.client");
        final URI uri = UriBuilder.fromUri(Jaxrs2Client.BASE_URI).port(9527).build();
        server = GrizzlyHttpServerFactory.createHttpServer(uri, rc);
    }

    @After
    public void end() {
        if (server != null) {
            server.shutdown();
        }
    }
}
