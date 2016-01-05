package com.example.resource;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.Book;
import com.example.domain.Books;
import com.example.service.BookService;

/**
 * <p>BookResource class.</p>
 *
 * @author hanl
 * @version $Id: $Id
 */

@Path("books")
@javax.annotation.security.RunAs("user")
public class BookResource {
    private static final Logger LOGGER = Logger.getLogger(BookResource.class);
    @Autowired
    private BookService bookService;

    /**
     * <p>getBooks.</p>
     *
     * @return a {@link com.example.domain.Books} object.
     */
    //@RolesAllowed(value={"admin"})
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Books getBooks(@Context final SecurityContext sc) {
        logMe(sc);
        final Books books = bookService.getBooks();
        BookResource.LOGGER.debug(books);
        return books;
    }

    private void logMe(final SecurityContext sc) {
        try {
            BookResource.LOGGER.info("User=" + sc.getUserPrincipal().getName());
            BookResource.LOGGER.info("User Role?=" + sc.isUserInRole("user"));
            BookResource.LOGGER.info("Auth way=" + sc.getAuthenticationScheme());
        } catch (final Exception e) {
            LOGGER.debug("Cannot print credential info."+e);
        }
    }

    /**
     * <p>getBookByPath.</p>
     *
     * @param bookId a {@link java.lang.Integer} object.
     * @return a {@link com.example.domain.Book} object.
     */
    @Path("{bookId:[0-9]*}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Book getBookByPath(@PathParam("bookId") final Integer bookId) {
        final Book book = bookService.getBook(bookId);
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
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Book getBookByQuery(@QueryParam("id") final Integer bookId) {
        final Book book = bookService.getBook(bookId);
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
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    public Book saveBook(@Context final SecurityContext sc, final Book book) {
        logMe(sc);
        if (sc.isUserInRole("admin")) {
            return bookService.saveBook(book);
        } else {
            return book;
        }
    }

    /**
     * <p>updateBook.</p>
     *
     * @param bookId a {@link java.lang.Integer} object.
     * @param book a {@link com.example.domain.Book} object.
     * @return a {@link com.example.domain.Book} object.
     */
    @Path("{bookId:[0-9]*}")
    @PUT
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    public Book updateBook(@PathParam("bookId") final Integer bookId, final Book book) {
        if (book == null) {
            return null;
        }
        return bookService.updateBook(bookId, book);
    }

    /**
     * <p>deleteBook.</p>
     *
     * @param bookId a {@link java.lang.Integer} object.
     * @return a {@link java.lang.String} object.
     */
    @Path("/{bookId:[0-9]*}")
    @RolesAllowed("admin")
    @DELETE
    public String deleteBook(@PathParam("bookId") final Integer bookId) {
        if (bookService.deleteBook(bookId)) {
            return "Deleted book id=" + bookId;
        } else {
            return "Deleted book failed id=" + bookId;
        }
    }
}
