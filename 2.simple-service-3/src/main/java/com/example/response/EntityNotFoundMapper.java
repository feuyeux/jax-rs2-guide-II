package com.example.response;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.example.exception.Jaxrs2GuideNotFoundException;

@Provider
public class EntityNotFoundMapper implements ExceptionMapper<Jaxrs2GuideNotFoundException> {

    @Override
    public Response toResponse(final Jaxrs2GuideNotFoundException ex) {
        return Response.status(404).entity(ex.getMessage()).type("text/plain").build();
    }
}