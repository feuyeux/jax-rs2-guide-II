package com.examples.sse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;

@Path("pubsub")
public class AirSsePubSubResource {
    private static final Logger log = LogManager.getLogger(AirSsePubSubResource.class);
    private static EventOutput eventOutput = new EventOutput();

    @GET
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput publishMessage() throws IOException {
        return eventOutput;
    }

    @GET
    @Path("slow")
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput publishLongMessage() {
        final EventOutput eventOutput0 = new EventOutput();
        log.info("Start to open sse channel.");
        new Thread() {
            public void run() {
                try {
                    for (int i = 1; i <= 5; i++) {
                        eventOutput0.write(new OutboundEvent.Builder().name("handling progress").data(String.class, (i * 20) + "%").build());
                    }
                    eventOutput0.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return eventOutput0;
    }

    @POST
    public void saveMessage(String message) throws IOException {
        log.info("What the client post: {}", message);
        eventOutput.write(new OutboundEvent.Builder().id(System.nanoTime() + "").name("post-message").data(String.class, message).build());
    }
}
