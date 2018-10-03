package org.feuyeux.restful.web;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;
import javax.ws.rs.container.ConnectionCallback;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.feuyeux.restful.domain.Book;
import org.feuyeux.restful.domain.Books;
import org.springframework.stereotype.Component;

@Component
@Path("books")
@Produces({"application/json;charset=UTF-8", "application/javascript;charset=UTF-8", "text/javascript;charset=UTF-8"})
public class AsyncResource {
    public static final long TIMEOUT = 120;
    private static final Logger log = LogManager.getLogger(AsyncResource.class);
    final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    @GET
    public void getAll(@Suspended final AsyncResponse asyncResponse) {
        configResponse(asyncResponse);
        final BatchRunner batchTask = new BatchRunner(asyncResponse);
        threadPool.submit(batchTask);
    }

    private void configResponse(final AsyncResponse asyncResponse) {
        asyncResponse.register((CompletionCallback)throwable -> {
            if (throwable == null) {
                log.info("CompletionCallback-onComplete: OK");
            } else {
                log.info("CompletionCallback-onComplete: ERROR: " + throwable.getMessage());
            }
        });

        asyncResponse.register((ConnectionCallback)disconnected -> {
            //Status.GONE=410
            log.info("ConnectionCallback-onDisconnect");
            disconnected.resume(Response.status(Response.Status.GONE).entity("disconnect!").build());
        });

        asyncResponse.setTimeoutHandler(r -> {
            //Status.SERVICE_UNAVAILABLE=503
            log.info("TIMEOUT");
            r.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Operation time out.").build());
        });
        asyncResponse.setTimeout(TIMEOUT, TimeUnit.SECONDS);
    }

    class BatchRunner implements Runnable {
        private final AsyncResponse response;

        public BatchRunner(AsyncResponse asyncResponse) {
            this.response = asyncResponse;
        }

        @Override
        public void run() {
            try {
                Books books = doBatch();
                response.resume(books);
            } catch (InterruptedException e) {
                log.error(e);
            }
        }

        private Books doBatch() throws InterruptedException {
            Books books = new Books();
            for (int i = 0; i < 10; i++) {
                Thread.sleep(500);
                Book book = new Book(i + 10000L, "Java RESTful Web Services", "华章");
                log.debug(book);
                books.getBookList().add(book);
            }
            return books;
        }
    }
}