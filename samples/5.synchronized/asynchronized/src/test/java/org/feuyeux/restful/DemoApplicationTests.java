package org.feuyeux.restful;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private final RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void ok() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("http://localhost:" + this.port + "/hello" + "/ok", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        log.info(entity.getBody());
    }

    @Test
    public void testAsync() throws InterruptedException, ExecutionException {
        final Invocation.Builder request = target("http://localhost:" + this.port + "/books").request();
        final AsyncInvoker async = request.async();
        final Future<Books> responseFuture = async.get(Books.class);
        log.debug("First response time = " + System.currentTimeMillis());
        Books result = null;
        try {
            result = responseFuture.get(AsyncResource.TIMEOUT + 1, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            log.debug("", e);
        } finally {
            log.debug("Second response time = " + System.currentTimeMillis());
            log.debug(result.getBookList().size());
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
