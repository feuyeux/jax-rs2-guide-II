package org.feuyeux.restful.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Component
@Path("hello")
@Produces({"application/javascript;charset=UTF-8", "application/json;charset=UTF-8", "text/javascript;charset=UTF-8"})
public class HelloResource {
    private static final Log log = LogFactory.getLog(HelloResource.class);

    @GET
    @Path("/ok")
    public String ok() {
        log.debug(System.currentTimeMillis());
        return "OK" + System.getProperty("line.separator");
    }
}
