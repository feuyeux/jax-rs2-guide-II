package com.example.response;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

import com.example.exception.TestException;

@Path("/te")
public class TestExceptionResource {

    @DELETE
    public void testEx(){
        
        throw new TestException("Text Exception");
    }
    
}
