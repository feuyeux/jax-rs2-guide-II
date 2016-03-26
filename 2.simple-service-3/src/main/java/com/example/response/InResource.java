package com.example.response;

import com.example.exception.Jaxrs2GuideNotFoundException;
import org.apache.log4j.Logger;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.*;

/**
 * Root resource (exposed at "inResource" path)
 */
@Path("in-resource")
public class InResource {
    private static final Logger LOGGER = Logger.getLogger(InResource.class);

    @POST
    @Path("b")
    public String postBytes(final byte[] bs) {
        for (final byte b : bs) {
            LOGGER.debug(b);
        }
        return "byte[]:" + new String(bs);
    }

    /*
    public GenericEntity<String> get(final byte[] bs) {
        for (final byte b : bs) {
            LOGGER.debug(b);
        }
        return new GenericEntity<String>("byte[]:" + new String(bs), String.class);
    }
    */

    @POST
    @Path("c")
    public Response postString(final String s) {
        LOGGER.debug(s);
        //Response.noContent().build();
        return Response.ok().entity("char[]:" + s).build();
    }

    @DELETE
    @Path("{s}")
    public void delete(@PathParam("s") final String s) {
        LOGGER.debug(s);
    }

    @POST
    @Path("f")
    public File postFile(final File f) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String s;
            do {
                s = br.readLine();
                LOGGER.debug(s);
            } while (s != null);
            return f;
        }
    }

    @POST
    @Path("bio")
    public String postStream(final InputStream is) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder result = new StringBuilder();
            String s = br.readLine();
            while (s != null) {
                result.append(s).append("\n");
                LOGGER.debug(s);
                s = br.readLine();
            }
            return result.toString();
        }
    }

    @POST
    @Path("cio")
    public Response postChars(final Reader r) throws IOException {
        try (BufferedReader br = new BufferedReader(r)) {
            StringBuilder result = new StringBuilder();
            String s = br.readLine();
            if (s == null) {
                throw new Jaxrs2GuideNotFoundException("NOT FOUND FROM READER");
            }
            while (s != null) {
                result.append(s).append("\n");
                LOGGER.debug(s);
                s = br.readLine();
            }
            return Response.ok().entity(result.toString()).build();
        }
    }
}
