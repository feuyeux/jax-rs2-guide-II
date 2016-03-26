package com.example.link;

import com.example.domain.Book;
import com.example.domain.BookWrapper;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HATEOASTest extends JerseyTest {

    private static final Logger LOGGER = Logger.getLogger(HATEOASTest.class);

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(HATEOASResource.class);
    }

    @Test
    public void testHateoas() {
        Builder request = target("hateoas-resource").request(MediaType.APPLICATION_XML_TYPE);
        Entity<Book> entity = Entity.entity(new Book("HATEOAS"), MediaType.APPLICATION_XML_TYPE);
        final BookWrapper b = request.post(entity, BookWrapper.class);
        LOGGER.debug("POSTED::");
        LOGGER.debug(b.getLink());
        LOGGER.debug(b.getBookId());
        LOGGER.debug(b.getBookName());
        LOGGER.debug(b.getPublisher());
        Assert.assertEquals("HATEOAS", b.getBookName());

        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target(b.getLink());
        final Response response = target.request().get();
        final Book book = response.readEntity(Book.class);
        LOGGER.debug("GET::" + book);
        Assert.assertEquals("HATEOAS", book.getBookName());
    }
}
