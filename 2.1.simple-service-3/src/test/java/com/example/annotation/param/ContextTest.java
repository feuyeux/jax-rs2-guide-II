package com.example.annotation.param;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;

public class ContextTest extends JerseyTest {
    private static final Logger LOGGER = Logger.getLogger(ContextTest.class);

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(ContextResource.class);
    }

    @Test
    public void testContexts() {
        final String path = "ctx-resource";
        String result;

        /*http://localhost:9998/ctx-resource/Asia/China/northeast/liaoning/shenyang/huangu*/
        final WebTarget pathTarget = target(path).path("Asia").path("China").path("northeast").path("liaoning").path("shenyang").path("huangu");
        result = pathTarget.request().get().readEntity(String.class);
        ContextTest.LOGGER.debug(result);

        /*http://localhost:9998/ctx-resource/China/shenyang/tiexi?station=Workers+Village&vehicle=bus*/
        final WebTarget queryTarget = target(path).path("China").path("shenyang").path("tiexi").queryParam("station", "Workers Village")
                .queryParam("vehicle", "bus");
        result = queryTarget.request().get().readEntity(String.class);
        ContextTest.LOGGER.debug(result);
    }
}
