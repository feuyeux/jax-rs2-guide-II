package com.example.resource.interceptor;

import org.apache.log4j.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;

@Provider
public class AirWriterInterceptor implements WriterInterceptor {
    private static final Logger LOGGER = Logger.getLogger(AirWriterInterceptor.class);

    public AirWriterInterceptor() {
        LOGGER.info("AirWriterInterceptor initialized");
    }

    @Override
    public void aroundWriteTo(final WriterInterceptorContext context) throws IOException, WebApplicationException {
    }
}
