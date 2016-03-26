package com.example.com.example.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Path("rest")
public class MyResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            if (i % 10 == 0) {
                result.append(">").append(i);
            }
        }
        return result.toString();
    }

    @GET
    @Path("v1.0")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getIt1(@Context UriInfo uriInfo) {
        final UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        final URI uri = ub.replacePath("rest/v2.0").build();
        return Response.accepted().link(uri, "currentVersion").build();
    }

    @GET
    @Path("v2.0")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt2() {
        StringBuilder result = new StringBuilder("v2");
        for (int i = 0; i < 100; i++) {
            if (i % 10 == 0) {
                result.append(">").append(i);
            }
        }
        return result.toString();
    }

    @GET
    @Path("head-version")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt3(@Context final HttpHeaders headers) {
        String version = headers.getRequestHeaders().get("X-API-Version").get(0);
        if (version.equals("2"))
            return getIt2();
        else
            return getIt();
    }

    @GET
    @Path("cache_control")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getItWithCacheControl() {
        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(1200);
        cacheControl.setMustRevalidate(true);
        return Response.ok(getIt()).cacheControl(cacheControl).build();
    }

    @GET
    @Path("expires")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getItWithExpires() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date date = cal.getTime();
        return Response.ok(getIt()).expires(date).build();
    }

    static ConcurrentHashMap<String, Date> map = new ConcurrentHashMap<>();

    @GET
    @Path("last_modified")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getItWithLastModified(@QueryParam("userId") String userId, @Context Request request) {
        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(1200);
        Date lastModified = map.get(userId);
        Response.ResponseBuilder rb = null;
        if (lastModified != null) {
            rb = request.evaluatePreconditions(lastModified);
        }
        if (rb != null) {
            return rb.cacheControl(cacheControl).build();
        } else {
            Date date = new Date();
            map.put(userId, date);
            return Response.ok(getIt()).cacheControl(cacheControl).lastModified(date).build();
        }
    }

    @GET
    @Path("e_tag")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getItWithETag(@QueryParam("userId") String userId, @Context Request request) {
        Response.ResponseBuilder rb;
        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(1200);
        EntityTag tag = new EntityTag(userId.hashCode() + "");
        rb = request.evaluatePreconditions(tag);
        if (rb != null) {
            return rb.cacheControl(cacheControl).build();
        } else {
            return Response.ok(getIt()).cacheControl(cacheControl).tag(tag).build();
        }
    }
}
