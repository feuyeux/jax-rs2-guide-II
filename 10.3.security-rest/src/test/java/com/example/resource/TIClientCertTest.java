package com.example.resource;

import static org.junit.Assert.assertEquals;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.SslConfigurator;
import org.junit.Test;

import com.example.domain.Book;
import com.example.domain.Books;

public class TIClientCertTest {
    public static final String BASE_URI = "https://mars64:8443/security-rest/webapi/books/";

    private Client buildSecureClient(boolean admin) {
        String keystore;
        if (admin) {
            keystore = "D:\\-aries\\github\\jax-rs2-guide\\sample\\6\\security-rest\\keystore\\restAdminClient.keystore";
        } else {
            keystore = "D:\\-aries\\github\\jax-rs2-guide\\sample\\6\\security-rest\\keystore\\restUserClient.keystore";
        }
        final SslConfigurator sslConfig = SslConfigurator.newInstance().trustStoreFile(keystore).trustStorePassword("restful").keyStoreFile(keystore)
                .keyPassword("restful");

        final SSLContext sslContext = sslConfig.createSSLContext();
        final Client client = ClientBuilder.newBuilder().sslContext(sslContext).build();
        return client;
    }

    //when BookResource.getBooks uses:: @RolesAllowed(value={"admin"}) 
    //Then:: @Test(expected = javax.ws.rs.ForbiddenException.class)
    @Test
    public void testGetAll() {
        final Client client = buildSecureClient(false);
        final Invocation.Builder invocationBuilder = client.target(BASE_URI).request();
        invocationBuilder.get(Books.class);
    }

    @Test(expected = javax.ws.rs.ForbiddenException.class)
    public void testPost() {
        final Book newBook = new Book("Java Restful Web Service使用指南-" + System.nanoTime());
        final Entity<Book> bookEntity = Entity.entity(newBook, MediaType.APPLICATION_JSON_TYPE);
        buildSecureClient(false).target(BASE_URI).request(MediaType.APPLICATION_JSON_TYPE).post(bookEntity, Book.class);
    }

    @Test
    public void testPost2() {
        String bookName = "Java Restful Web Service使用指南-" + System.nanoTime();
        final Book newBook = new Book(bookName);
        final Entity<Book> bookEntity = Entity.entity(newBook, MediaType.APPLICATION_JSON_TYPE);
        Book posted = buildSecureClient(true).target(BASE_URI).request(MediaType.APPLICATION_JSON_TYPE).post(bookEntity, Book.class);
        assertEquals(bookName, posted.getBookName());
    }
}
