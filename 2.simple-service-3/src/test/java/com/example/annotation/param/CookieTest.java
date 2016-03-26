package com.example.annotation.param;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Application;

public class CookieTest extends JerseyTest {
    private static final Logger LOGGER = Logger.getLogger(CookieTest.class);

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(CookieResource.class);
    }

    @Test
    public void testContexts() {
        final String path = "kuky-resource";
        String result;

        /*http://localhost:9998/kuky-resource*/
        final Builder request = target(path).request();
        request.cookie("longitude", "123.38");
        request.cookie("latitude", "41.8");
        request.cookie("population", "822.8");
        request.cookie("area", "12948");
        result = request.get().readEntity(String.class);
        CookieTest.LOGGER.debug(result);
        Assert.assertEquals("123.38,41.8 population=822.8,area=12948", result);
    }
}
