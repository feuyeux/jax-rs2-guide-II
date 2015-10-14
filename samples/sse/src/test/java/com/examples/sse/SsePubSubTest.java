package com.examples.sse;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;
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

import com.example.client.AtupClientUtil;

public class SsePubSubTest extends JerseyTest {
    private static final Logger LOG = Logger.getLogger(SsePubSubTest.class);
    private static final String ROOT_PATH = "pubsub";
    private static final int READ_TIMEOUT = 30000;

    @Override
    protected Application configure() {
        return new ResourceConfig(AirSsePubSubResource.class, SseFeature.class);
    }

    /*@Override
    protected void configureClient(ClientConfig config) {
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", getPort(), PlainSocketFactory.getSocketFactory()));

        PoolingClientConnectionManager cm = new PoolingClientConnectionManager(registry);
        cm.setMaxTotal(MAX_LISTENERS * MAX_ITEMS);
        cm.setDefaultMaxPerRoute(MAX_LISTENERS * MAX_ITEMS);

        config.register(SseFeature.class).property(ApacheClientProperties.CONNECTION_MANAGER, cm).property(ClientProperties.READ_TIMEOUT, READ_TIMEOUT)
                .connector(new ApacheConnector(config));
    }*/
    
	@Override
	protected void configureClient(ClientConfig config) {
		AtupClientUtil.buildeApacheConfig(config);
		config.property(ClientProperties.READ_TIMEOUT, READ_TIMEOUT);
		config.register(SseFeature.class);
	}

    @Test
    public void testEventSource() throws InterruptedException, URISyntaxException {
        final int testCount = 10;
        final String messagePrefix = "pubsub-";
        final CountDownLatch latch = new CountDownLatch(testCount);
        final EventSource eventSource = new EventSource(target().path(ROOT_PATH)) {
            private int i;

            @Override
            public void onEvent(InboundEvent inboundEvent) {
                try {
                    LOG.info("Received: " + inboundEvent.getId() + ":" + inboundEvent.getName() + ":" + new String(inboundEvent.getRawData()));
                    Assert.assertEquals(messagePrefix + i++, inboundEvent.readData(String.class));
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
                        LOG.info("# Received: " + event);
                        LOG.info(event.readData(String.class));
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
            LOG.info(inboundEvent.getName() + inboundEvent.readData(String.class));
        }
    }
}