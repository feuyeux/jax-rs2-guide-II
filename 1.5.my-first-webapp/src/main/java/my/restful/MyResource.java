package my.restful;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    private static ConcurrentHashMap<String, MyDomain> map=new ConcurrentHashMap<>();

    @GET
    @Path("{key}")
    @Produces(MediaType.APPLICATION_XML)
    public MyDomain getMy(@PathParam("key") final String key) {
        final MyDomain myDomain = map.get(key);
        if (myDomain == null) {
            return new MyDomain();
        }
        return myDomain;
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void addMy(final MyDomain myDomain) {
        map.put(myDomain.getName(), myDomain);
    }
}
