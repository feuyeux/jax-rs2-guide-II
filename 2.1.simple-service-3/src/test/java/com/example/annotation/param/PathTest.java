package com.example.annotation.param;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;

public class PathTest extends JerseyTest {
    private static final Logger LOGGER = Logger.getLogger(PathTest.class);
    private static final String path = "path-resource";

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(PathResource.class);
    }

    @Test
    public void testBasic() {
        String result;
        /*http://localhost:9998/path-resource/Eric*/
        result = target(path).path("Eric").request().get().readEntity(String.class);
        Assert.assertEquals("Eric:Shen Yang", result);

        /*http://localhost:9998/path-resource/Eric?hometown=Buenos Aires*/
        /*http://localhost:9998/path-resource/Eric?hometown=Buenos+Aires*/
        result = target(path).path("Eric").queryParam("hometown", "Buenos Aires").request().get().readEntity(String.class);
        Assert.assertEquals("Eric:Buenos Aires", result);
    }

    @Test
    public void testPunctuation() {
        String result;
        /*http://localhost:9998/path-resource/199-1999*/
        result = target(path).path("199-1999").request().get().readEntity(String.class);
        PathTest.LOGGER.debug(result);
        Assert.assertEquals("from=199:to=1999", result);

        /*http://localhost:9998/path-resource/01,2012-12,2014*/
        result = target(path).path("01,2012-12,2014").request().get().readEntity(String.class);
        PathTest.LOGGER.debug(result);
        Assert.assertEquals("2012.01~2014.12", result);
    }

    @Test
    public void testRegion() {
        String result;
        /*http://localhost:9998/path-resource/Asia/China/northeast/liaoning/shenyang/huangu*/
        final WebTarget pathTarget = target(path).path("Asia").path("China").path("northeast").path("liaoning").path("shenyang").path("huangu");
        result = pathTarget.request().get().readEntity(String.class);
        PathTest.LOGGER.debug(result);
        Assert.assertEquals("Asia-China-northeast-liaoning-shenyang-huangu", result);
    }

    @Test
    public void testQ() {
        String result;
        /*http://localhost:9998/path-resource/q/restful;program=java;type=web*/
        result = target(path).path("q").path("restful;program=java;type=web").request().get().readEntity(String.class);
        PathTest.LOGGER.debug(result);
        Assert.assertEquals("restful program=[java] type=[web] ", result);
        /*http://localhost:9998/path-resource/q2/restful;program=java;type=web*/
        result = target(path).path("q2").path("restful;program=java;type=web").request().get().readEntity(String.class);
        PathTest.LOGGER.debug(result);
        Assert.assertEquals("restful program=[java] type=[web]", result);
    }
}
