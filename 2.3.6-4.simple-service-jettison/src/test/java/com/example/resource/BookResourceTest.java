package com.example.resource;

import com.example.domain.Book;
import com.example.domain.Books;
import com.example.jettison.JsonBook;
import com.example.jettison.JsonBook2;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jettison.JettisonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public class BookResourceTest extends JerseyTest {
    private static final Logger LOGGER = Logger.getLogger(BookResourceTest.class);

    @Override
    protected ResourceConfig configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        ResourceConfig resourceConfig = new ResourceConfig(BookResource.class);
        resourceConfig.register(JettisonFeature.class);
        resourceConfig.register(JsonContextResolver.class);
        return resourceConfig;
    }

    @Override
    protected void configureClient(ClientConfig config) {
        config.register(new JettisonFeature()).register(JsonContextResolver.class);
    }

    @Test
    public void testJsonBook() {
        JsonBook book = target("books").path("jsonbook").request(MediaType.APPLICATION_JSON).get(JsonBook.class);
        LOGGER.debug(book);
        //{"jsonBook":{"bookId":1,"bookName":"abc"}}
    }

    @Test
    public void testJsonBook2() {
        JsonBook2 book = target("books").path("jsonbook2").request(MediaType.APPLICATION_JSON).get(JsonBook2.class);
        LOGGER.debug(book);
        //{"jsonBook2":{"bookId":{"$":"1"},"bookName":{"$":"abc"}}}
    }

    @Test
    public void testGettingBooks() {
        Books books = target("books").request(MediaType.APPLICATION_JSON_TYPE).get(Books.class);
        for (Book book : books.getBookList()) {
            LOGGER.debug(book);
        }
    }

    @Test
    public void testPost() {
        Entity<Book> e = Entity.entity(new Book(4L, "Java Restful Web Services实战II", "机械工业出版社")
                , MediaType.APPLICATION_JSON_TYPE);
        Book book = target("books").request(MediaType.APPLICATION_JSON_TYPE).post(e, Book.class);
        LOGGER.debug(book);
    }
}