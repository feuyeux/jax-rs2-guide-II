package com.example.resource;


import com.example.AirAopConfig;
import com.example.domain.Book;
import com.example.domain.Books;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

public class TestNamingBinding extends JerseyTest {
    private static final String BASE_URI = "books/";

    @Override
    protected Application configure() {
        return new AirAopConfig();
    }

    @Test
    public void testPathGetJSON() {
        final WebTarget pathTarget = target(BASE_URI).path("1");
        final Invocation.Builder invocationBuilder = pathTarget.request(MediaType.APPLICATION_JSON_TYPE);
        final Book result = invocationBuilder.get(Book.class);
        Assert.assertNotNull(result.getBookId());
    }

    @Test
    public void testGetAll() {
        final Invocation.Builder invocationBuilder = target(BASE_URI).request();
        final Books result = invocationBuilder.get(Books.class);
        Assert.assertNotNull(result.getBookList());
    }
}
