package com.example.resource;

import com.example.domain.Book;
import com.example.domain.Books;
import com.example.resource.bing.AirLog;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * <p>BookResource class.</p>
 *
 * @author hanl
 * @version $Id: $Id
 */
@Path("books")
public class BookResource {
    private static final Logger LOGGER = Logger.getLogger(BookResource.class);
    private static final HashMap<Long, Book> memoryBase;

    static {
        memoryBase = com.google.common.collect.Maps.newHashMap();
        BookResource.memoryBase.put(1L, new Book(1L, "Java Restful Web Service实战", "cmpbook"));
        BookResource.memoryBase.put(2L, new Book(2L, "JSF2和RichFaces4实战", "phei"));
    }

    /**
     * <p>getBooks.</p>
     *
     * @return a {@link com.example.domain.Books} object.
     */
    @AirLog
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Books getBooks() {
        final List<Book> bookList = new ArrayList<>();
        final Collection<Book> bookCol = BookResource.memoryBase.values();
        final Iterator<Book> iterator = bookCol.iterator();
        while (iterator.hasNext()) {
            bookList.add(iterator.next());
        }
        final Books books = new Books(bookList);
        BookResource.LOGGER.debug("books=" + books);
        return books;
    }

    /**
     * <p>getBookByPath.</p>
     *
     * @param bookId a {@link java.lang.Integer} object.
     * @return a {@link com.example.domain.Book} object.
     */
    @Path("{bookId:[0-9]*}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Book getBookByPath(@PathParam("bookId") final Long bookId) {
        final Book book = BookResource.memoryBase.get(bookId);
        BookResource.LOGGER.debug(book);
        return book;
    }

    /**
     * <p>getBookByQuery.</p>
     *
     * @param bookId a {@link java.lang.Integer} object.
     * @return a {@link com.example.domain.Book} object.
     */
    @Path("/book")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Book getBookByQuery(@QueryParam("id") final Long bookId) {
        final Book book = BookResource.memoryBase.get(bookId);
        BookResource.LOGGER.debug(book);
        return book;
    }

    /**
     * <p>saveBook.</p>
     *
     * @param book a {@link com.example.domain.Book} object.
     * @return a {@link com.example.domain.Book} object.
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML})
    public Book saveBook(final Book book) {
        book.setBookId(System.nanoTime());
        BookResource.memoryBase.put(book.getBookId(), book);
        return book;
    }
}
