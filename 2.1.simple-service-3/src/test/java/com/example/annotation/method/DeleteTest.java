package com.example.annotation.method;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

public class DeleteTest extends JerseyTest {
    private final static Logger LOGGER = Logger.getLogger(DeleteTest.class);

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(EBookResourceImpl.class);
    }

    @Test
    public void testGet() {
        final Response response = target("book").queryParam("bookId", "9527").request().delete();
        int status = response.getStatus();
        LOGGER.debug(status);
        Assert.assertEquals(Response.Status.NO_CONTENT.getStatusCode(), status);
    }
}
