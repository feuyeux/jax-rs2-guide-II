package com.examples.sse;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import javax.inject.Singleton;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;
import org.glassfish.jersey.media.sse.SseFeature;
import org.glassfish.jersey.server.ChunkedOutput;

@Singleton
@Path("broadcast")
public class AirSseBroadcastResource {
    private static final Logger log = LogManager.getLogger(AirSseBroadcastResource.class);
    private static final BlockingQueue<BroadcastProcess> processQueue = new LinkedBlockingQueue<>(1);

    @Path("book")
    @POST
    public Boolean postBook(@DefaultValue("0") @QueryParam("total") int total, String bookName) {
        final BroadcastProcess broadcastProcess = new BroadcastProcess(total, bookName);
        processQueue.add(broadcastProcess);
        Executors.newSingleThreadExecutor().execute(broadcastProcess);
        return true;
    }

    @Path("book/clear")
    @DELETE
    public Boolean clear() {
        processQueue.clear();
        return true;
    }

    @Path("book")
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    @GET
    public EventOutput getBook(@DefaultValue("0") @QueryParam("clientId") int clientId) throws InterruptedException {
        log.info("request from client {}", clientId);
        BroadcastProcess broadcastProcess = processQueue.peek();
        if (broadcastProcess != null) {
            broadcastProcess.countDown();
            final EventOutput eventOutput = new EventOutput();
            broadcastProcess.getBroadcaster().add(eventOutput);
            return eventOutput;
        } else {
            throw new NotFoundException("No new broadcast.");
        }
    }

    static class BroadcastProcess implements Runnable {
        private final long processId;
        private final String bookName;
        private final CountDownLatch latch;
        private final SseBroadcaster broadcaster = new SseBroadcaster() {
            @Override
            public void onException(ChunkedOutput<OutboundEvent> out, Exception e) {
                log.error("SSE Broadcast ERROR", e);
            }
        };

        public BroadcastProcess(int total, String bookName) {
            this.processId = System.nanoTime();
            this.bookName = bookName;
            log.info("initialize BroadcastProcess: process({}) processId={},,bookName={}", total, processId, bookName);
            latch = total > 0 ? new CountDownLatch(total) : null;
        }

        public long getProcessId() {
            return processId;
        }

        public SseBroadcaster getBroadcaster() {
            return broadcaster;
        }

        public long countDown() {
            if (latch == null) {
                return -1L;
            }
            latch.countDown();
            return latch.getCount();
        }

        public void run() {
            try {
                if (latch != null) {
                    latch.await();
                }
                OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder().mediaType(MediaType.TEXT_PLAIN_TYPE);
                OutboundEvent event = eventBuilder.id(processId + "").name("New Book Name").data(String.class, bookName)
                    .build();
                broadcaster.broadcast(event);
                broadcaster.closeAll();
            } catch (InterruptedException e) {
                log.error("", e);
            }
        }
    }
}
