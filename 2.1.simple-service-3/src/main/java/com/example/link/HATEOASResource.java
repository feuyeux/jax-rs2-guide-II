package com.example.link;

import com.example.domain.Book;
import com.example.domain.BookWrapper;
import com.example.exception.Jaxrs2GuideNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;


@Path("hateoas-resource")
public class HATEOASResource {
    @Context
    UriInfo uriInfo;

    @Path("{bookId:[0-9]*}")
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public Book getOne(@PathParam("bookId") final Long bookId) {
        final Book result = LinkCache.map.get(bookId);
        if (result == null) {
            throw new Jaxrs2GuideNotFoundException("Not found for bookId=" + bookId);
        }
        return result;
    }


    @POST
    @Produces({MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_XML})
    public BookWrapper saveBook(final Book book) {
        final long newId = System.nanoTime();
        book.setBookId(newId);
        LinkCache.map.put(newId, book);

        final UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        final URI uri = ub.path("" + newId).build();
        BookWrapper b = new BookWrapper();
        b.setBook(book);
        b.setLink(uri.toString());
        return b;
    }
}
