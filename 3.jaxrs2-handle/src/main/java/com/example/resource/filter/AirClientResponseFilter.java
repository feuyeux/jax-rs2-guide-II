package com.example.resource.filter;

import java.io.IOException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;

public class AirClientResponseFilter implements ClientResponseFilter {

  @Override
  public void filter(
      final ClientRequestContext requestContext, final ClientResponseContext responseContext)
      throws IOException {
    // TODO Auto-generated method stub
  }
}
