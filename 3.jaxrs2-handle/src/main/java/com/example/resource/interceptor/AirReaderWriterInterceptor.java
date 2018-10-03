package com.example.resource.interceptor;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.apache.log4j.Logger;

@Provider
public class AirReaderWriterInterceptor implements ReaderInterceptor, WriterInterceptor {
    private static final Logger LOGGER = Logger.getLogger(AirReaderWriterInterceptor.class);

    public AirReaderWriterInterceptor() {
        LOGGER.info("AirReaderInterceptor initialized");
    }

    @Override
    public Object aroundReadFrom(final ReaderInterceptorContext context) throws IOException, WebApplicationException {
        Object entity = context.proceed();
        LOGGER.debug(entity);
        return entity;
    }

    @Override
    public void aroundWriteTo(final WriterInterceptorContext context) throws IOException, WebApplicationException {
        LOGGER.debug(context.getEntity());
        context.proceed();
    }
}
