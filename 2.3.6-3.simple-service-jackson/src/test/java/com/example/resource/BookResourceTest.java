package com.example.resource;

import com.example.domain.Book;
import com.example.domain.Books;
import com.example.jackson.JsonBook;
import com.example.jackson.JsonHybridBook;
import com.example.jackson.JsonNoJaxbBook;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.util.runner.ConcurrentRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

@RunWith(ConcurrentRunner.class)
public class BookResourceTest extends JerseyTest {
    private static final Logger LOGGER = Logger.getLogger(BookResourceTest.class);

    @Override
    protected ResourceConfig configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        ResourceConfig resourceConfig = new ResourceConfig(BookResource.class);
        resourceConfig.register(JacksonFeature.class).register(JsonContextProvider.class);
        return resourceConfig;
    }

    @Override
    protected void configureClient(ClientConfig config) {
        config.register(new JacksonFeature()).register(JsonContextProvider.class);
    }

    public void testEmptyArray() {
        JsonBook book = target("books").path("emptybook").request(MediaType.APPLICATION_JSON).get(JsonBook.class);
        LOGGER.debug(book);
    }

    public void testHybrid() {
        JsonHybridBook book = target("books").path("hybirdbook").request(MediaType.APPLICATION_JSON).get(JsonHybridBook.class);
        LOGGER.debug(book);
    }

    @Test
    public void testNoJaxb() {
        JsonNoJaxbBook book = target("books").path("nojaxbbook").request(MediaType.APPLICATION_JSON).get(JsonNoJaxbBook.class);
        LOGGER.debug(book);
    }

    @Test
    public void testGetBooks() {
        Books books = target("books").request(MediaType.APPLICATION_JSON_TYPE).get(Books.class);
        for (Book book : books.getBookList()) {
            LOGGER.debug(book.getBookName());
        }
    }

    @Test
    public void testPost() {
        Entity<Book> e = Entity.entity(new Book(4L, "Java Restful Web Services实战II", "机械工业出版社"), MediaType.APPLICATION_JSON_TYPE);
        Book book = target("books").request(MediaType.APPLICATION_JSON_TYPE).post(e, Book.class);
        LOGGER.debug(book);
    }
}