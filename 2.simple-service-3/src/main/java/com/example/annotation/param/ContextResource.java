package com.example.annotation.param;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Path("ctx-resource")
public class ContextResource {
    @GET
    @Path("{region:.+}/shenyang/{district:\\w+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getByAddress(
        @Context final Application application,
        @Context final Request request,
        @Context final javax.ws.rs.ext.Providers provider,
        @Context final UriInfo uriInfo,
        @Context final HttpHeaders headers) {
        final StringBuilder buf = new StringBuilder();
        final String path = uriInfo.getPath();
        buf.append("PATH=").append(path).append("\n");

        final MultivaluedMap<String, String> pathMap = uriInfo.getPathParameters();
        buf.append("PATH_PARAMETERS:\n");
        iterating(buf, pathMap);

        final MultivaluedMap<String, String> queryMap = uriInfo.getQueryParameters();
        buf.append("QUERY_PARAMETERS:\n");
        iterating(buf, queryMap);

        final List<PathSegment> segmentList = uriInfo.getPathSegments();
        buf.append("PATH_SEGMENTS:\n");
        for (final PathSegment pathSegment : segmentList) {
            final MultivaluedMap<String, String> matrix = pathSegment.getMatrixParameters();
            final String segmentPath = pathSegment.getPath();
            buf.append(matrix);
            buf.append(segmentPath);
        }
        buf.append("\nHEAD:\n");
        final MultivaluedMap<String, String> headerMap = headers.getRequestHeaders();
        iterating(buf, headerMap);
        buf.append("COOKIE:\n");
        final Map<String, Cookie> kukyMap = headers.getCookies();
        for (Entry<String, Cookie> e : kukyMap.entrySet()) {
            final String k = e.getKey();
            buf.append("key=").append(k).append(",value=");
            final Cookie cookie = e.getValue();
            buf.append(cookie.getValue()).append(" ");
            buf.append("\n");
        }
        return buf.toString();
    }

    private void iterating(final StringBuilder buf, final MultivaluedMap<String, String> pathMap) {
        for (Entry<String, List<String>> e : pathMap.entrySet()) {
            final String k = e.getKey();
            buf.append("key=").append(k).append(",value=");
            final List<String> vList = e.getValue();
            for (final String v : vList) {
                buf.append(v).append(" ");
            }
            buf.append("\n");
        }
    }
}
