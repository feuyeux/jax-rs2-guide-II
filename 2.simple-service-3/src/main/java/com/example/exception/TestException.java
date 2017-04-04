package com.example.exception;

import javax.ws.rs.WebApplicationException;

public class TestException extends WebApplicationException {

    /**
     * 
     */
    private static final long serialVersionUID = -4882461278043195570L;

    public TestException(int status) {
        super(status);
    }

    public TestException(String message) {
        super(message);
    }

}
