package com.example.link;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.example.domain.Book;
import com.example.exception.Jaxrs2GuideNotFoundException;

@Path("weblink-resource")
public class WebLinkResource {
    @Context
    UriInfo uriInfo;

    @Path("{bookId:[0-9]*}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Book getOne(@PathParam("bookId") final Long bookId) {
        final Book result = LinkCache.map.get(bookId);
        if (result == null) {
            throw new Jaxrs2GuideNotFoundException("Not found for bookId=" + bookId);
        }
        return result;
    }

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML})
    public Response saveBook(final Book book) {
        final long newId = System.nanoTime();
        book.setBookId(newId);
        LinkCache.map.put(newId, book);
        /**
         UriInfo的 resolve(java.net.URI)使用应用上下文将相对路径转换成绝对路径
         UriInfo.relativize(java.net.URI)将绝对路径转换成相对路径
         UriBuilder 相对路径模板
         **/
        final UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        final URI location = ub.path("" + newId).build();

        final String uriTemplate = "http://{host}:{port}/{path}/{param}";
        final URI location2 = UriBuilder.fromUri(uriTemplate).resolveTemplate("host", "localhost").resolveTemplate(
            "port", "9998")
            .resolveTemplate("path", "weblink-resource").resolveTemplate("param", newId).build();

        final UriBuilder ub3 = uriInfo.getAbsolutePathBuilder();
        final URI location3 = ub3.scheme("http").host("localhost").port(9998).path("weblink-resource").path("" + newId)
            .build();

        return Response.created(location).link(location2, "view1").link(location3, "view2").entity(book).build();
    }
}
