package com.example.client;

import com.example.client.entity.Book;
import java.util.Map;
import java.util.Map.Entry;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;

public class Jaxrs2Client {
  public static final String BASE_URI = "http://localhost:9527/client/";
  protected static final Logger LOGGER = Logger.getLogger(Jaxrs2Client.class);
  protected Client client;
  protected ClientConfig clientConfig;

  protected void checkConfig() {
    final Configuration newConfiguration = client.getConfiguration();
    final Map<String, Object> properties = newConfiguration.getProperties();
    for (Entry<String, Object> next : properties.entrySet()) {
      Jaxrs2Client.LOGGER.debug(next.getKey() + ":" + next.getValue());
    }
  }

  public void test() {
    final WebTarget webTarget = client.target(Jaxrs2Client.BASE_URI);
    final WebTarget pathTarget = webTarget.path("books");
    final WebTarget pathTarget2 = pathTarget.path("book");
    final WebTarget queryTarget = pathTarget2.queryParam("bookId", "1");
    Jaxrs2Client.LOGGER.debug(queryTarget.getUri());
    final Invocation.Builder invocationBuilder = queryTarget.request(MediaType.APPLICATION_XML);
    /*response.readEntity will close the connect*/
    final Book book = invocationBuilder.get(Book.class);
    Jaxrs2Client.LOGGER.debug(book);
  }
}
