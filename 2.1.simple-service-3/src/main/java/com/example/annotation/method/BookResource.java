package com.example.annotation.method;

import com.example.domain.Book;
import com.example.domain.Books;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("book")
public interface BookResource {

    @GET
    public String getWeight();

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_XML)
    public String newBook(Book book);

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Book createBook(Book book);

    @DELETE
    public void delete(@QueryParam("bookId") final long bookId);

    @MOVE
    public boolean moveBooks(Books books);
}
