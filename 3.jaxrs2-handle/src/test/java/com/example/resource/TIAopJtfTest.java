package com.example.resource;

import com.example.AirAopConfig;
import com.example.domain.Books;
import org.apache.log4j.Logger;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Application;

/**
 * Integration Test by Jersey Test Framework
 *
 * @author hanl
 */
public class TIAopJtfTest extends JerseyTest {
    private static final Logger LOGGER = Logger.getLogger(TIAopJtfTest.class);
    private static final String BASEURI = "books/";

    @Override
    protected Application configure() {
        //enable(org.glassfish.jersey.test.TestProperties.LOG_TRAFFIC);
        //enable(org.glassfish.jersey.test.TestProperties.DUMP_ENTITY);
        return new AirAopConfig(BookResource.class);
    }

    @Test
    public void testGetAll() {
        TIAopJtfTest.LOGGER.debug(">>Test Get All");
        final Invocation.Builder invocationBuilder = target(TIAopJtfTest.BASEURI).request();
        final Books result = invocationBuilder.get(Books.class);
        TIAopJtfTest.LOGGER.debug(result.getBookList());
        Assert.assertNotNull(result.getBookList());
        TIAopJtfTest.LOGGER.debug("<<Test Get All");
    }
}
