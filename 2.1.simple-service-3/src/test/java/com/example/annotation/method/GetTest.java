package com.example.annotation.method;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

public class GetTest extends JerseyTest {
    @Override
    protected Application configure() {
        return new ResourceConfig(EBookResourceImpl.class);
    }

    @Test
    public void testGet() {
        final Response response = target("book").request().get();
        Assert.assertEquals("150M", response.readEntity(String.class));
    }
}
