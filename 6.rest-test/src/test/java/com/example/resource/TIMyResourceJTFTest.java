package com.example.resource;

import com.example.domain.Book;
import com.example.domain.Books;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TIMyResourceJTFTest extends JerseyTest {
    private static final String BASEURI = "books/";
    static final String CONTAINER_GRIZZLY = "org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory";
    static final String CONTAINER_MEMORY = "org.glassfish.jersey.test.inmemory.InMemoryTestContainerFactory";
    static final String CONTAINER_JDK = "org.glassfish.jersey.test.jdkhttp.JdkHttpServerTestContainerFactory";
    static final String CONTAINER_SIMPLE = "org.glassfish.jersey.test.simple.SimpleTestContainerFactory";

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        //set(TestProperties.CONTAINER_FACTORY, CONTAINER_SIMPLE);
        return new ResourceConfig(BookResource.class);
    }


    @Test
    public void testQueryGetXML() {
        System.out.println(">>Test Query Get");
        final WebTarget queryTarget = target(TIMyResourceJTFTest.BASEURI + "book").queryParam("id", Integer.valueOf(1));
        final Invocation.Builder invocationBuilder = queryTarget.request(MediaType.APPLICATION_XML_TYPE);
        final Response response = invocationBuilder.get();
        final Book result = response.readEntity(Book.class);
        System.out.println(result);
        Assert.assertNotNull(result.getBookId());
        System.out.println("<<Test Query Get");
    }

    @Test
    public void testPathGetJSON() {
        System.out.println(">>Test Path Get");
        final WebTarget pathTarget = target(TIMyResourceJTFTest.BASEURI + "/1");
        final Invocation.Builder invocationBuilder = pathTarget.request(MediaType.APPLICATION_JSON_TYPE);
        final Book result = invocationBuilder.get(Book.class);
        System.out.println(result);
        Assert.assertNotNull(result.getBookId());
        System.out.println("<<Test Path Get");
    }

    @Test
    public void testPostAndDelete() {
        System.out.println(">>Test Post");
        final Book newBook = new Book("Java Restful Web Service实战-" + System.nanoTime());
        final Entity<Book> bookEntity = Entity.entity(newBook, MediaType.APPLICATION_JSON_TYPE);
        final Book savedBook = target(TIMyResourceJTFTest.BASEURI).request(MediaType.APPLICATION_JSON_TYPE).post(bookEntity, Book.class);
        Assert.assertNotNull(savedBook.getBookId());
        System.out.println("<<Test Post");

        System.out.println(">>Test Delete");
        final WebTarget deleteTarget = target(TIMyResourceJTFTest.BASEURI + savedBook.getBookId());
        final Invocation.Builder invocationBuilder = deleteTarget.request();
        final String result = invocationBuilder.delete(String.class);
        System.out.println(result);
        Assert.assertNotNull(result);
        System.out.println("<<Test Delete");
    }

    @Test
    public void testGetAll() {
        System.out.println(">>Test Get All");
        final Invocation.Builder invocationBuilder = target(TIMyResourceJTFTest.BASEURI).request();
        final Books result = invocationBuilder.get(Books.class);
        System.out.println(result.getBookList());
        Assert.assertNotNull(result.getBookList());
        System.out.println("<<Test Get All");
    }
}
