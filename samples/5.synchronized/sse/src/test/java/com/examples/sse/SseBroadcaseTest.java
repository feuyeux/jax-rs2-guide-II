package com.examples.sse;

import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.media.sse.EventListener;
import org.glassfish.jersey.media.sse.EventSource;
import org.glassfish.jersey.media.sse.InboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import com.example.client.AtupClientUtil;

public class SseBroadcaseTest extends JerseyTest {
	private static final Logger LOG = Logger.getLogger(SseBroadcaseTest.class);

	private final int MAX_COUNT = 3;
	private final CountDownLatch doneLatch = new CountDownLatch(MAX_COUNT);
	private final EventSource[] readerEventSources = new EventSource[MAX_COUNT];
	private final String newBookName = "Java Restful Web Services实战";

	@Override
	protected Application configure() {
		return new ResourceConfig(AirSseBroadcastResource.class,
				SseFeature.class);
	}

	/*
	 * @Override protected void configureClient(ClientConfig config) {
	 * SchemeRegistry registry = new SchemeRegistry(); registry.register(new
	 * Scheme("http", getPort(), PlainSocketFactory.getSocketFactory()));
	 * 
	 * PoolingClientConnectionManager cm = new
	 * PoolingClientConnectionManager(registry); cm.setMaxTotal(MAX_LISTENERS *
	 * MAX_ITEMS); cm.setDefaultMaxPerRoute(MAX_LISTENERS * MAX_ITEMS);
	 * 
	 * config.property(ApacheClientProperties.CONNECTION_MANAGER, cm);
	 * config.property(ClientProperties.READ_TIMEOUT, 2000);
	 * config.register(SseFeature.class); ApacheConnector connector = new
	 * ApacheConnector(config); config.connector(connector); }
	 */

	@Override
	protected void configureClient(ClientConfig config) {
		AtupClientUtil.buildeApacheConfig(config);
		config.property(ClientProperties.READ_TIMEOUT, 2000);
		config.register(SseFeature.class);
	}

	@Test
	public void testBroadcast() throws InterruptedException, URISyntaxException {
		final Builder request = target().path("broadcast/book")
				.queryParam("total", MAX_COUNT).request();
		final Boolean posted = request.post(Entity.text(newBookName),
				Boolean.class);
		Assert.assertTrue(posted);
		for (int i = 0; i < MAX_COUNT; i++) {
			final WebTarget endpoint = target().path("broadcast/book")
					.queryParam("clientId", i + 1);
			readerEventSources[i] = EventSource.target(endpoint).build();
			readerEventSources[i].register(new EventListener() {
				@Override
				public void onEvent(InboundEvent inboundEvent) {
					try {
						StringBuilder receives = new StringBuilder("Received: ");
						receives.append(inboundEvent.getId()).append(":");
						receives.append(inboundEvent.getName()).append(":");
						receives.append(new String(inboundEvent.getRawData()));
						LOG.info(receives.toString());
						Assert.assertEquals(newBookName,
								inboundEvent.readData(String.class));
						doneLatch.countDown();
					} catch (ProcessingException e) {
						e.printStackTrace();
					}
				}
			});
			readerEventSources[i].open();
		}

		doneLatch.await();
		for (EventSource source : readerEventSources) {
			source.close();
		}
	}
}
