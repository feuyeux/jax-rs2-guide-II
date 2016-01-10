package com.example.annotation.method;

import com.example.domain.Book;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

public class PutTest extends JerseyTest {
    private final static Logger LOGGER = Logger.getLogger(PutTest.class);
    public static AtomicLong clientBookSequence = new AtomicLong();

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(EBookResourceImpl.class);
    }

    @Test
    public void testNew() {
        final Book newBook = new Book(clientBookSequence.incrementAndGet(), "book-" + System.nanoTime());
        MediaType contentTypeMediaType = MediaType.APPLICATION_XML_TYPE;
        MediaType acceptMediaType = MediaType.TEXT_PLAIN_TYPE;
        final Entity<Book> bookEntity = Entity.entity(newBook, contentTypeMediaType);
        final String lastUpdate = target("book").request(acceptMediaType).put(bookEntity, String.class);
        Assert.assertNotNull(lastUpdate);
        LOGGER.debug(lastUpdate);
    }
}
