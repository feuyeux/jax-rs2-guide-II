package com.example.resource;

import com.example.domain.Book;
import com.example.domain.Books;
import com.example.resource.bing.AirLog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

/**
 * BookResource class.
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
   * getBooks.
   *
   * @return a {@link com.example.domain.Books} object.
   */
  @AirLog
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Books getBooks() {
    final List<Book> bookList = new ArrayList<>();
    final Collection<Book> bookCol = BookResource.memoryBase.values();
    bookList.addAll(bookCol);
    final Books books = new Books(bookList);
    BookResource.LOGGER.debug("books=" + books);
    return books;
  }

  /**
   * getBookByPath.
   *
   * @param bookId a {@link java.lang.Integer} object.
   * @return a {@link com.example.domain.Book} object.
   */
  @Path("{bookId:[0-9]*}")
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Book getBookByPath(@PathParam("bookId") final Long bookId) {
    final Book book = BookResource.memoryBase.get(bookId);

    return book;
  }

  /**
   * getBookByQuery.
   *
   * @param bookId a {@link java.lang.Integer} object.
   * @return a {@link com.example.domain.Book} object.
   */
  @Path("/book")
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Book getBookByQuery(@QueryParam("id") final Long bookId) {
    final Book book = BookResource.memoryBase.get(bookId);

    return book;
  }

  /**
   * saveBook.
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
