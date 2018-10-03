package org.feuyeux.restful;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.feuyeux.restful.domain.Books;
import org.feuyeux.restful.web.AsyncResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.ws.rs.client.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class DemoApplicationTests {
    private static final Logger log = LogManager.getLogger(DemoApplicationTests.class);
    private final TestRestTemplate restTemplate = new TestRestTemplate();
    @Value("${local.server.port}")
    private int port;

    @Test
    public void ok() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity(
            "http://localhost:" + this.port + "/hello" + "/ok", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        log.info(entity.getBody());
    }

    @Test
    public void testAsync() throws InterruptedException, ExecutionException {
        final Invocation.Builder request = target("http://localhost:" + this.port + "/books").request();
        final AsyncInvoker async = request.async();
        final Future<Books> responseFuture = async.get(Books.class);
        long beginTime = System.currentTimeMillis();
        try {
            Books result = responseFuture.get(AsyncResource.TIMEOUT + 1, TimeUnit.SECONDS);
            log.debug("Testing result size = {}", result.getBookList().size());
        } catch (TimeoutException e) {
            log.debug("Fail to request asynchronously", e);
        } finally {
            log.debug("Elapsed time = {}", System.currentTimeMillis() - beginTime);
        }
    }

    @Test
    public void testAsyncCallBack() throws InterruptedException, ExecutionException {
        final AsyncInvoker async = target("http://localhost:" + this.port + "/books").request().async();
        final Future<Books> responseFuture = async.get(new InvocationCallback<Books>() {
            @Override
            public void completed(Books result) {
                log.debug("On Completed: " + result.getBookList().size());
            }

            @Override
            public void failed(Throwable throwable) {
                log.debug("On Failed: " + throwable.getMessage());
                throwable.printStackTrace();
            }
        });
        log.debug("First response time::" + System.currentTimeMillis());
        try {
            responseFuture.get(AsyncResource.TIMEOUT + 1, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            log.debug("", e);
        } finally {
            log.debug("Second response time::" + System.currentTimeMillis());
        }
    }

    private WebTarget target(String url) {
        Client client = ClientBuilder.newClient();
        return client.target(url);
    }
}
