package com.example.resource;

import com.example.domain.Book;
import com.example.domain.Books;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

public class JsonTest extends JerseyTest {
    private final static Logger LOGGER = Logger.getLogger(JsonTest.class);

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(BookResource.class);
    }

    @Test
    public void testGettingBooks() {
        Books books = target("books").request(MediaType.APPLICATION_JSON_TYPE).get(Books.class);
        for (Book book : books.getBookList()) {
            LOGGER.debug(book.getBookName());
        }
    }
}
