package com.example.annotation.param;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;

public class BeanParamTest extends JerseyTest {
    private static final Logger LOGGER = Logger.getLogger(BeanParamTest.class);

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(BeanParamResource.class);
    }

    @Test
    public void testBeanParam() {
        final String path = "bean-resource";
        String result;

        /*http://localhost:9998/ctx-resource/China/shenyang/tiexi?station=Workers+Village&vehicle=bus*/
        final WebTarget queryTarget = target(path).path("China").path("northeast").path("shenyang").path("tiexi").queryParam("station", "Workers Village")
                .queryParam("vehicle", "bus");
        result = queryTarget.request().get().readEntity(String.class);
        LOGGER.debug(result);
        Assert.assertEquals("China/northeast:tiexi:Workers Village:bus", result);
    }
}
