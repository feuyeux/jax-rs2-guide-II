package com.example.link;

import com.example.domain.Book;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Set;

public class WebLinkTest extends JerseyTest {
    private static final Logger LOGGER = Logger.getLogger(WebLinkTest.class);

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(WebLinkResource.class);
    }

    @Test
    public void testPostAndGet() {
        final Entity<Book> entity = Entity.entity(new Book("WEBLINK"), MediaType.APPLICATION_XML_TYPE);
        final Response response = target("weblink-resource").request().post(entity, Response.class);
        final URI location = response.getLocation();
        WebLinkTest.LOGGER.debug(location.toString());
        final Set<Link> links = response.getLinks();
        for (final Link link : links) {
            WebLinkTest.LOGGER.debug(link);
        }
        final Book book = response.readEntity(Book.class);
        WebLinkTest.LOGGER.debug("POSTED::" + book);
        Assert.assertEquals("WEBLINK", book.getBookName());

        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target(location);
        final Response response2 = target.request().get();
        final Book book2 = response2.readEntity(Book.class);
        WebLinkTest.LOGGER.debug("GET::" + book2);
        Assert.assertEquals("WEBLINK", book2.getBookName());
    }
}
