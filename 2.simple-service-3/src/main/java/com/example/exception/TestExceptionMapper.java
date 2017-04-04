package com.example.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.example.domain.ErrorMessage;

/**
 * 
 * @author and0429
 *
 */

@Provider
public class TestExceptionMapper implements ExceptionMapper<TestException> {

    /*
     * (non-Javadoc)
     * 
     * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
     */
    @Override
    public Response toResponse(TestException exception) {

        ErrorMessage em = new ErrorMessage(404, exception.getMessage());
        
        Response resp = Response.status(Response.Status.NOT_FOUND)
                .entity(em)
                .type(MediaType.APPLICATION_JSON)
                .encoding("UTF-8")
                .build();

        return resp;

    }

}
