package com.example.resource;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class TestByteArrayReader extends JerseyTest {
  private static final Logger LOGGER = Logger.getLogger(TestByteArrayReader.class);

  @Override
  protected Application configure() {
    enable(org.glassfish.jersey.test.TestProperties.LOG_TRAFFIC);
    enable(org.glassfish.jersey.test.TestProperties.DUMP_ENTITY);
    return new ResourceConfig(ByteArrayResource.class);
  }

  @Test
  public void testReader() {
    Response response = target("test").request().get();
    byte[] result = response.readEntity(byte[].class);
    LOGGER.debug(new String(result));
  }
}
