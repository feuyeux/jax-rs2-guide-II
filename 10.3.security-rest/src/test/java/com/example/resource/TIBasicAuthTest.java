package com.example.resource;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Test;

import com.example.domain.Book;
import com.example.domain.Books;

/**
 * Need to launch Tomcat firstly
 * To use web.xml.1
 *
 * @author hanl
 */
public class TIBasicAuthTest {
    public static final String BASE_URI = "http://localhost:8080/security-rest/webapi/books/";

    @Test(expected = javax.ws.rs.NotAuthorizedException.class)
    public void testGetAll() {
        final ClientConfig cc = new ClientConfig();
        final Client client = ClientBuilder.newClient(cc);
        final Invocation.Builder invocationBuilder = client.target(BASE_URI).request();
        invocationBuilder.get(Books.class);
    }

    //when BookResource.getBooks uses:: @RolesAllowed(value={"admin"}) 
    //Then:: @Test(expected = javax.ws.rs.ForbiddenException.class)
    @Test
    public void testGetAll2() {
        final ClientConfig cc = getClientConfig();

        final Client client = ClientBuilder.newClient(cc);
        final Invocation.Builder invocationBuilder = client.target(BASE_URI).request();
        invocationBuilder.get(Books.class);
    }

    private ClientConfig getClientConfig() {
        final ClientConfig cc = new ClientConfig();
        /*2.5-*/
        //cc.register(new HttpBasicAuthFilter("caroline", "zhang"));
        /*2.5+*/
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder().nonPreemptive().credentials("caroline", "zhang").build();
        cc.register(feature);
        return cc;
    }

    @Test(expected = javax.ws.rs.ForbiddenException.class)
    public void testPost() {
        final ClientConfig cc = getClientConfig();
        final Client client = ClientBuilder.newClient(cc);
        final Book newBook = new Book("Java Restful Web Service使用指南-" + System.nanoTime());
        final Entity<Book> bookEntity = Entity.entity(newBook, MediaType.APPLICATION_JSON_TYPE);
        client.target(BASE_URI).request(MediaType.APPLICATION_JSON_TYPE).post(bookEntity, Book.class);
    }
}
