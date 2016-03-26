package com.example.media.xml;

import com.example.domain.Book;
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

public class XMLTest extends JerseyTest {
    private static final Logger LOGGER = Logger.getLogger(XMLTest.class);
    final String path = "xml-resource";
    Book book = new Book(100L, "TEST BOOK");

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(XMLResource.class);
    }

    @Test
    public void testStream() {
        final Builder request = target(path).path("stream").request();
        LOGGER.debug("STREAM");
        handleResult(request);
    }

    @Test
    public void testSAX() {
        final Builder request = target(path).path("sax").request();
        LOGGER.debug("SAX");
        handleResult(request);
    }

    @Test
    public void testDOM() {
        final Builder request = target(path).path("dom").request();
        LOGGER.debug("DOM");
        handleResult(request);
    }

    @Test
    public void testDOC() {
        final Builder request = target(path).path("doc").request();
        LOGGER.debug("DOC");
        handleResult(request);
    }

    @Test
    public void testJAXB0() {
        final Builder request = target(path).path("jaxb").request();
        LOGGER.debug("JAXB0");
        handleResult(request);
    }

    private void handleResult(final Builder request) {
        final Book result = request.post(Entity.entity(book, MediaType.APPLICATION_XML), Book.class);
        Assert.assertNotNull(result);
    }

    @Test
    public void testJAXB() {
        final Builder request = target(path).request();
        LOGGER.debug("JAXB");
        handleResult(request);
    }
}
