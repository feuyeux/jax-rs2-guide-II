package org.feuyeux.restful;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.feuyeux.restful.domain.Book;
import org.feuyeux.restful.domain.Books;
import org.feuyeux.restful.web.AsyncResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@IntegrationTest("server.port=0")
@WebAppConfiguration
public class DemoApplicationTests {
    private static final Log log = LogFactory.getLog(DemoApplicationTests.class);
    private static final int COUNT = 10;

    @Value("${local.server.port}")
    private int port;

    private RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void ok() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("http://localhost:" + this.port + "/hello" + "/ok", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }


    @Test
    public void testAsyncBatchSave() throws InterruptedException, ExecutionException {
        List<Book> bookList = new ArrayList<>(COUNT);
        log.debug("**->Test Batch Save");
        try {
            for (int i = 0; i < COUNT; i++) {
                final Book newBook = new Book(i + "-" + System.nanoTime());
                bookList.add(newBook);
            }
            Books books = new Books(bookList);
            final Entity<Books> booksEntity = Entity.entity(books, "application/json;charset=UTF-8");
            final Invocation.Builder request = target("http://localhost:" + this.port + "/books").request();
            final AsyncInvoker async = request.async();
            final Future<String> responseFuture = async.post(booksEntity, String.class);
            log.debug("First response @" + System.currentTimeMillis());
            String result = null;
            try {
                result = responseFuture.get(AsyncResource.TIMEOUT + 1, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                log.debug("%%%% " + e.getMessage());
            } finally {
                log.debug("Second response @" + System.currentTimeMillis());
                log.debug("<<<<<<<<<<< book id array: " + result);
            }
        } finally {
            log.debug("<-**Test Batch Save");
        }
    }

    private WebTarget target(String url) {
        Client client = ClientBuilder.newClient();
        return client.target(url);
    }

    @Test
    public void testAsyncBatchSaveCallBack() throws InterruptedException, ExecutionException {
        List<Book> bookList = new ArrayList<>(COUNT);
        log.debug("**->Test Batch Save");
        try {
            for (int i = 0; i < COUNT; i++) {
                final Book newBook = new Book(i + "-" + System.nanoTime());
                bookList.add(newBook);
            }
            Books books = new Books(bookList);
            final Entity<Books> booksEntity = Entity.entity(books, MediaType.APPLICATION_XML_TYPE);
            final AsyncInvoker async = target("http://localhost:" + this.port + "/books").request().async();
            final Future<String> responseFuture = async.post(booksEntity, new InvocationCallback<String>() {
                @Override
                public void completed(String result) {
                    log.debug("On Completed: " + result);
                }

                @Override
                public void failed(Throwable throwable) {
                    log.debug("On Failed: " + throwable.getMessage());
                    throwable.printStackTrace();
                }
            });
            log.debug("First response @" + System.currentTimeMillis());
            String result = null;
            try {
                result = responseFuture.get(AsyncResource.TIMEOUT + 1, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                log.debug("%%%% " + e.getMessage());
            } finally {
                log.debug("Second response @" + System.currentTimeMillis());
                log.debug("<<<<<<<<<<< book id array: " + result);
            }
        } finally {
            log.debug("<-**Test Batch Save");
        }
    }
}
