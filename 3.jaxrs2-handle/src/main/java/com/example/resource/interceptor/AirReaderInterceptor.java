package com.example.resource.interceptor;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import org.apache.log4j.Logger;

@Provider
public class AirReaderInterceptor implements ReaderInterceptor {
    private static final Logger LOGGER = Logger.getLogger(AirReaderInterceptor.class);

    public AirReaderInterceptor() {
        LOGGER.info("AirReaderInterceptor initialized");
    }

    @Override
    public Object aroundReadFrom(final ReaderInterceptorContext context) throws IOException, WebApplicationException {
        return null;
    }
}
