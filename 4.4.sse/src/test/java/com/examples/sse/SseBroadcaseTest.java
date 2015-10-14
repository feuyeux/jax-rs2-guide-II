package com.examples.sse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.media.sse.EventSource;
import org.glassfish.jersey.media.sse.SseFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

public class SseBroadcaseTest extends JerseyTest {
    private static final Logger log = LogManager.getLogger(SseBroadcaseTest.class);

    private final int MAX_COUNT = 3;
    private final CountDownLatch doneLatch = new CountDownLatch(MAX_COUNT);
    private final EventSource[] readerEventSources = new EventSource[MAX_COUNT];
    private final String newBookName = "Java Restful Web Services实战II";

    @Override
    protected Application configure() {
        return new ResourceConfig(
                AirSseBroadcastResource.class,
                SseFeature.class);
    }

    @Override
    protected void configureClient(ClientConfig config) {
        ClientUtil.buildApacheConfig(config);
        config.property(ClientProperties.READ_TIMEOUT, 2000);
        config.register(SseFeature.class);
    }

    @Test
    public void testBroadcast() throws InterruptedException, URISyntaxException {
        final Invocation.Builder request = target().path("broadcast/book").queryParam("total", MAX_COUNT).request();
        final Boolean posted = request.post(Entity.text(newBookName), Boolean.class);
        Assert.assertTrue(posted);

        for (int i = 0; i < MAX_COUNT; i++) {
            final WebTarget endpoint = target().path("broadcast/book").queryParam("clientId", i + 1);


















        }

        doneLatch.await();
        for (EventSource source : readerEventSources) {
            source.close();
        }
    }
}
