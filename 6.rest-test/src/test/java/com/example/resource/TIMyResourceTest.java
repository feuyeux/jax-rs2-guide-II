package com.example.resource;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.domain.Book;
import com.example.domain.Books;
import com.example.sevice.TUMyServiceTest;

/**
 * Integration Test
 * 
 * @author hanl
 *
 */
//@org.junit.Ignore
public class TIMyResourceTest {
    private final static Logger LOGGER = Logger.getLogger(TUMyServiceTest.class);
    public static final String BASE_URI = "http://localhost:8080/webapi/";
    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        final ResourceConfig rc = new ResourceConfig().packages("com.example");
        final URI uri = URI.create(TIMyResourceTest.BASE_URI);
        server = GrizzlyHttpServerFactory.createHttpServer(uri, rc, false);
        server.start();
        final ClientConfig cc = new ClientConfig();
        final Client client = ClientBuilder.newClient(cc);
        target = client.target(TIMyResourceTest.BASE_URI).path("books");
    }

    @After
    public void tearDown() throws Exception {
        if (server != null) {
            server.shutdown();
        }
    }

    @Test
    public void testQueryGetXML() {
        TIMyResourceTest.LOGGER.debug(">>Test Query Get");
        final WebTarget queryTarget = target.path("/book").queryParam("id", Integer.valueOf(1));
        final Invocation.Builder invocationBuilder = queryTarget.request(MediaType.APPLICATION_XML_TYPE);
        final Response response = invocationBuilder.get();
        final Book result = response.readEntity(Book.class);
        TIMyResourceTest.LOGGER.debug(result);
        Assert.assertNotNull(result.getBookId());
        TIMyResourceTest.LOGGER.debug("<<Test Query Get");
    }

    @Test
    public void testPathGetJSON() {
        TIMyResourceTest.LOGGER.debug(">>Test Path Get");
        final WebTarget pathTarget = target.path("/1");
        final Invocation.Builder invocationBuilder = pathTarget.request(MediaType.APPLICATION_JSON_TYPE);
        final Book result = invocationBuilder.get(Book.class);
        TIMyResourceTest.LOGGER.debug(result);
        Assert.assertNotNull(result.getBookId());
        TIMyResourceTest.LOGGER.debug("<<Test Path Get");
    }

    @Test
    public void testPostAndDelete() {
        TIMyResourceTest.LOGGER.debug(">>Test Post");
        final Book newBook = new Book("Java Restful Web Service实战-" + System.nanoTime());
        final Entity<Book> bookEntity = Entity.entity(newBook, MediaType.APPLICATION_JSON_TYPE);
        final Book savedBook = target.request(MediaType.APPLICATION_JSON_TYPE).post(bookEntity, Book.class);
        Assert.assertNotNull(savedBook.getBookId());
        TIMyResourceTest.LOGGER.debug("<<Test Post");

        TIMyResourceTest.LOGGER.debug(">>Test Delete");
        final WebTarget deleteTarget = target.path("/" + savedBook.getBookId());
        final Invocation.Builder invocationBuilder = deleteTarget.request();
        final String result = invocationBuilder.delete(String.class);
        TIMyResourceTest.LOGGER.debug(result);
        Assert.assertNotNull(result);
        TIMyResourceTest.LOGGER.debug("<<Test Delete");
    }

    @Test
    public void testGetAll() {
        TIMyResourceTest.LOGGER.debug(">>Test Get All");
        final Invocation.Builder invocationBuilder = target.request();
        final Books result = invocationBuilder.get(Books.class);
        TIMyResourceTest.LOGGER.debug(result.getBookList());
        Assert.assertNotNull(result.getBookList());
        TIMyResourceTest.LOGGER.debug("<<Test Get All");
    }
}
