package com.example.resource;

import com.example.domain.Book;
import com.example.domain.Books;
import com.example.service.BookService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

@Path("books")
public class BookResource {
  @Autowired private BookService bookService;

  /**
   * getBooks.
   *
   * @return a {@link com.example.domain.Books} object.
   */
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Books getBooks() {
    return bookService.getBooks();
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
    return bookService.getBook(bookId);
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
    return bookService.getBook(bookId);
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
    System.out.println(book);
    return bookService.saveBook(book);
  }

  /**
   * updateBook.
   *
   * @param bookId a {@link java.lang.Integer} object.
   * @param book a {@link com.example.domain.Book} object.
   * @return a {@link com.example.domain.Book} object.
   */
  @Path("{bookId:[0-9]*}")
  @PUT
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML})
  public Book updateBook(@PathParam("bookId") final Long bookId, final Book book) {
    if (book == null) {
      return null;
    }
    return bookService.updateBook(bookId, book);
  }

  /**
   * deleteBook.
   *
   * @param bookId a {@link java.lang.Integer} object.
   * @return a {@link java.lang.String} object.
   */
  @Path("/{bookId:[0-9]*}")
  @DELETE
  public String deleteBook(@PathParam("bookId") final Long bookId) {
    if (bookService.deleteBook(bookId)) {
      return "Deleted book id=" + bookId;
    } else {
      return "Deleted book failed id=" + bookId;
    }
  }
}
