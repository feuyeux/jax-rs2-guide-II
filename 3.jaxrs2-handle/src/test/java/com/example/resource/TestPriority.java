package com.example.resource;

import com.example.domain.Books;
import com.example.resource.bing.AirNameBindingFilter;
import com.example.resource.bing.AirNameBindingFilter2;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Application;

public class TestPriority extends JerseyTest {
    private static final String BASE_URI = "books/";

    @Override
    protected Application configure() {
        ResourceConfig config = new ResourceConfig(BookResource.class);
        config.register(AirNameBindingFilter.class);
        config.register(AirNameBindingFilter2.class);
        return config;
    }

    @Test
    public void testGetAll() {
        final Invocation.Builder invocationBuilder = target(BASE_URI).request();
        final Books result = invocationBuilder.get(Books.class);
        Assert.assertNotNull(result.getBookList());
    }
}
