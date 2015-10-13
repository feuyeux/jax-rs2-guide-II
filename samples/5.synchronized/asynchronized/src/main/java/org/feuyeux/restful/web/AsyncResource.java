package org.feuyeux.restful.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.feuyeux.restful.domain.Book;
import org.feuyeux.restful.domain.Books;
import org.springframework.util.CollectionUtils;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.*;

@Path("books")
@Produces({"application/javascript;charset=UTF-8", "application/json;charset=UTF-8", "text/javascript;charset=UTF-8"})
public class AsyncResource {
    private static final Logger log = LogManager.getLogger(AsyncResource.class);
    public static final long TIMEOUT = 120;

    public AsyncResource() {
    }

    @POST
    public void asyncBatchSave(@Suspended final AsyncResponse asyncResponse, final Books books) {
        configResponse(asyncResponse);
        final BatchRunner batchTask = new BatchRunner(books.getBookList());
        Future<String> bookIdsFuture = Executors.newSingleThreadExecutor().submit(batchTask);
        log.debug("submitted.");
        String ids;
        try {
            log.debug("getting result...");
            ids = bookIdsFuture.get();
            log.debug("To resume");
            asyncResponse.resume(ids);
            log.debug("Resume done");
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
        }
    }

    private void configResponse(final AsyncResponse asyncResponse) {
        asyncResponse.register(new CompletionCallback() {
            @Override
            public void onComplete(Throwable throwable) {
                if (throwable == null) {
                    log.info("CompletionCallback-onComplete: OK");
                } else {
                    log.info("CompletionCallback-onComplete: ERROR: " + throwable.getMessage());
                }
            }
        });

        asyncResponse.register(new ConnectionCallback() {
            @Override
            public void onDisconnect(AsyncResponse disconnected) {
                //Status.GONE=410
                log.info("ConnectionCallback-onDisconnect");
                disconnected.resume(Response.status(Response.Status.GONE).entity("disconnect!").build());
            }
        });

        asyncResponse.setTimeoutHandler(new TimeoutHandler() {
            @Override
            public void handleTimeout(AsyncResponse asyncResponse) {
                //Status.SERVICE_UNAVAILABLE=503
                log.info("TIMEOUT");
                asyncResponse.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Operation time out.").build());
            }
        });
        asyncResponse.setTimeout(TIMEOUT, TimeUnit.SECONDS);
    }

    class BatchRunner implements Callable<String> {
        private final List<Book> bookList;

        public BatchRunner(List<Book> bookList) {
            this.bookList = bookList;
        }

        @Override
        public String call() {
            String ids = null;
            try {
                ids = batchSave();
                log.info(">>>>>>>>>> " + ids);
            } catch (InterruptedException e) {
                log.error(e);
            }
            return ids;
        }

        private String batchSave() throws InterruptedException {
            if (!CollectionUtils.isEmpty(bookList)) {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < bookList.size(); i++) {
                    for (int j = 0; j < 1000; j++) {
                        if (j % 1000 == 0) {
                            Thread.sleep(500);
                            log.info("" + i);
                        }
                    }
                    Book book = bookList.get(i);
                    book.setBookId(i + 10000l);
                    book.setPublisher("华章");
                    log.info("saving book::" + book);
                    result.append(book).append(" ");
                }
                return result.toString();
            } else {
                return "";
            }
        }
    }
}