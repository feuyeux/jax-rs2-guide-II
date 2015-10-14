package com.examples.sse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ChunkedInput;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.media.sse.EventInput;
import org.glassfish.jersey.media.sse.EventSource;
import org.glassfish.jersey.media.sse.InboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SsePubSubTest extends JerseyTest {
    private static final Logger log = LogManager.getLogger(SsePubSubTest.class);
    private static final String ROOT_PATH = "pubsub";
    private static final int READ_TIMEOUT = 30000;
    private static final int testCount = 10;
    private static final String messagePrefix = "pubsub-";

    @Override
    protected Application configure() {
        return new ResourceConfig(AirSsePubSubResource.class, SseFeature.class);
    }

    @Override
    protected void configureClient(ClientConfig config) {
        ClientUtil.buildApacheConfig(config);
        config.property(ClientProperties.READ_TIMEOUT, READ_TIMEOUT);
        config.register(SseFeature.class);
    }

    @Test
    public void testEventSource() throws InterruptedException, URISyntaxException {
        final CountDownLatch latch = new CountDownLatch(testCount);
        final EventSource eventSource = new EventSource(target().path(ROOT_PATH)) {
            private int i;

            @Override
            public void onEvent(InboundEvent inboundEvent) {
                try {
                    String data = inboundEvent.readData(String.class);
                    log.info("What the server response: {}:{}:{}",
                            inboundEvent.getId(),
                            inboundEvent.getName(),
                            data);
                    Assert.assertEquals(messagePrefix + i++, data);
                    latch.countDown();
                } catch (ProcessingException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < testCount; i++) {
            target().path(ROOT_PATH).request().post(Entity.text(messagePrefix + i));
        }
        try {
            latch.await();
        } finally {
            eventSource.close();
        }
    }

    @Test
    public void testInboundEventReader() throws InterruptedException {
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch stopLatch = new CountDownLatch(5);
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final WebTarget target = target(ROOT_PATH);
                target.register(SseFeature.class);
                final EventInput eventInput = target.request().get(EventInput.class);
                startLatch.countDown();
                try {
                    eventInput.setParser(ChunkedInput.createParser("\n\n"));
                    do {
                        InboundEvent event = eventInput.read();
                        log.info("What the server response: {}", event);
                        assertNotNull(event.readData());
                        stopLatch.countDown();
                    } while (stopLatch.getCount() > 0);
                } catch (ProcessingException e) {
                    e.printStackTrace();
                } finally {
                    if (eventInput != null) {
                        eventInput.close();
                    }
                }
            }
        });
        thread.start();

        assertTrue(startLatch.await(5, TimeUnit.SECONDS));

        for (int i = 0; i < 5; i++) {
            target(ROOT_PATH).request().post(Entity.text("message " + i));
        }
        assertTrue(stopLatch.await(2, TimeUnit.SECONDS));
        thread.join(2000);
    }

    @Test
    public void testSubscribe() {
        final WebTarget target = target(ROOT_PATH);
        target.register(SseFeature.class);
        EventInput eventInput = target.path("slow").request().get(EventInput.class);
        while (!eventInput.isClosed()) {
            final InboundEvent inboundEvent = eventInput.read();
            if (inboundEvent == null) {
                // connection has been closed
                break;
            }
            log.info("[{}] {}", inboundEvent.getName(), inboundEvent.readData(String.class));
        }
    }
}