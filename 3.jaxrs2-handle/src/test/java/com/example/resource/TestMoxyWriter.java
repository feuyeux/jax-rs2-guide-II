package com.example.resource;

import com.example.domain.Book;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

public class TestMoxyWriter extends JerseyTest {
    private static final Logger LOGGER = Logger.getLogger(TestMoxyWriter.class);

    @Override
    protected Application configure() {
        ResourceConfig config = new ResourceConfig(ByteArrayResource.class);
        return config;
    }

    @Test
    public void testMoxyWriter() {
        final Book newBook = new Book("Java Restful Web Service实战-" + System.nanoTime());
        final Entity<Book> bookEntity = Entity.entity(newBook, MediaType.APPLICATION_JSON_TYPE);
        target("json").request(MediaType.APPLICATION_JSON_TYPE).post(bookEntity);
    }
}
