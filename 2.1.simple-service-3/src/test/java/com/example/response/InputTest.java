package com.example.response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.URL;

public class InputTest extends JerseyTest {
    private static final Logger LOGGER = Logger.getLogger(InputTest.class);
    final String path = "in-resource";
    String result;

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(InResource.class, EntityNotFoundMapper.class);
    }

    @Test
    public void testBytes() {
        final String message = "TEST STRING";
        final Builder request = target(path).path("b").request();
        //final Response response = request.post(Entity.entity(message, MediaType.TEXT_PLAIN_TYPE), Response.class);
        final Response response = request.post(Entity.entity(message, MediaType.TEXT_HTML_TYPE), Response.class);
        result = response.readEntity(String.class);
        LOGGER.debug(result);
        Assert.assertEquals("byte[]:" + message, result);
    }

    @Test
    public void testResponse() {
        final String message = "TEST STRING";
        final Builder request = target(path).path("c").request();
        final Response response = request.post(Entity.entity(message, MediaType.TEXT_HTML_TYPE), Response.class);
        result = response.readEntity(String.class);
        LOGGER.debug(result);
        Assert.assertEquals("char[]:" + message, result);
    }

    @Test
    public void testVoid() {
        final String message = "TEST STRING";
        final Builder request = target(path).path(message).request();
        final Response response = request.delete();
        int status = response.getStatus();
        LOGGER.debug(status);
        Assert.assertEquals(Response.Status.NO_CONTENT.getStatusCode(), status);
    }

    @Test
    public void testFile() throws IOException {
        final URL resource = getClass().getClassLoader().getResource("gua.txt");
        assert resource != null;
        final String file = resource.getFile();
        final File f = new File(file);
        final Builder request = target(path).path("f").request();
        Entity<File> e = Entity.entity(f, MediaType.TEXT_PLAIN_TYPE);
        final Response response = request.post(e, Response.class);
        File result = response.readEntity(File.class);
        try (BufferedReader br = new BufferedReader(new FileReader(result))) {
            String s;
            do {
                s = br.readLine();
                LOGGER.debug(s);
            } while (s != null);
        }
    }

    @Test
    public void testStream() {
        final InputStream resource = getClass().getClassLoader().getResourceAsStream("gua.txt");
        final Builder request = target(path).path("bio").request();
        Entity<InputStream> e = Entity.entity(resource, MediaType.TEXT_PLAIN_TYPE);
        final Response response = request.post(e, Response.class);
        result = response.readEntity(String.class);
        LOGGER.debug(result);
    }

    @Test
    public void testReader() {
        ClassLoader classLoader = getClass().getClassLoader();
        final Reader resource = new InputStreamReader(classLoader.getResourceAsStream("gua.txt"));
        final Builder request = target(path).path("cio").request();
        Entity<Reader> e = Entity.entity(resource, MediaType.TEXT_PLAIN_TYPE);
        final Response response = request.post(e, Response.class);
        result = response.readEntity(String.class);
        LOGGER.debug(result);
    }

    @Test
    public void testExceptionMapper() {
        Entity<Reader> e1 = Entity.entity(null, MediaType.TEXT_PLAIN_TYPE);
        final Response response1 = target(path).path("cio").request().post(e1, Response.class);
        result = response1.readEntity(String.class);
        LOGGER.debug(result);
        Assert.assertEquals("NOT FOUND FROM READER", result);
    }
}
