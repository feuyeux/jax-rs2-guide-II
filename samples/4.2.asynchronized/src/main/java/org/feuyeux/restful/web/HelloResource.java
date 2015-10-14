package org.feuyeux.restful.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Component
@Path("hello")
@Produces({"application/javascript;charset=UTF-8", "application/json;charset=UTF-8", "text/javascript;charset=UTF-8"})
public class HelloResource {
    private static final Logger log = LogManager.getLogger(HelloResource.class);

    @GET
    @Path("/ok")
    public String ok() {
        log.debug("Occurring time:{}", System.currentTimeMillis());
        return "OK" + System.getProperty("line.separator");
    }
}
