package com.example.conneg;

import com.example.domain.Book;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

public class ConnegTest extends JerseyTest {

    private static final Logger LOGGER = Logger.getLogger(ConnegTest.class);

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(ConnegResource.class);
    }

    @Test
    public void testGetJaxb() {
        LOGGER.debug("====XML====");
        WebTarget path = target("conneg-resource").path("123");
        Builder request = path.request(MediaType.APPLICATION_XML_TYPE);
        Book book = request.get(Book.class);
        LOGGER.debug(book);
    }

    @Test
    public void testGetJson() {
        LOGGER.debug("====JSON====");
        WebTarget path = target("conneg-resource").path("123");
        Builder request = path.request();
        request.header("Accept", "application/xml;q=0.1,application/json;q=0.2");
        Book book = request.get(Book.class);
        LOGGER.debug(book);
    }

    @Test
    public void testAccept() {
        LOGGER.debug("====CONNEG JSON====");
        WebTarget path = target("conneg-resource").path("book").path("123");
        Builder request = path.request();
        request.header("Accept", "application/xml;q=0.7,application/json;q=0.2");
        Book book = request.get(Book.class);
        LOGGER.debug(book);
    }

    @Test
    public void testContentLanguage() {
        LOGGER.debug("====CONNEG Language====");
        WebTarget path = target("conneg-resource");
        Builder request = path.request();
        request.header("content-language", "zh-cn");
        String s = request.get(String.class);
        LOGGER.debug(s);
    }
}
