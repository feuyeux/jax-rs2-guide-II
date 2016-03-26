package com.example.resource;

import com.example.domain.Book;
import com.example.domain.Books;
import com.example.jettison.JsonBook;
import com.example.jettison.JsonBook2;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import java.util.*;
import java.util.Map.Entry;

@Path("books")
public class BookResource {
    private static final Logger LOGGER = Logger.getLogger(BookResource.class);
    private static final HashMap<Long, Book> memoryBase;

    static {
        memoryBase = com.google.common.collect.Maps.newHashMap();
        memoryBase.put(1L, new Book(1L, "JSF2和RichFaces4使用指南", "电子工业出版社", "9787121177378", "2012-09-01"));
        memoryBase.put(2L, new Book(2L, "Java Restful Web Services实战", "机械工业出版社", "9787111478881", "2014-09-01"));
        memoryBase.put(3L, new Book(3L, "Java EE 7 精髓", "人民邮电出版社", "9787115375483", "2015-02-01"));
        memoryBase.put(4L, new Book(4L, "Java Restful Web Services实战II", "机械工业出版社"));
    }

    @Path("/jsonbook")
    @GET
    public JsonBook getBook() {
        final JsonBook book = new JsonBook();
        BookResource.LOGGER.debug(book);
        return book;
    }

    @Path("/jsonbook2")
    @GET
    public JsonBook2 getBook2() {
        final JsonBook2 book = new JsonBook2();
        BookResource.LOGGER.debug(book);
        return book;
    }

    @GET
    public Books getBooks() {
        final List<Book> bookList = new ArrayList<>();
        final Set<Map.Entry<Long, Book>> entries = BookResource.memoryBase.entrySet();
        final Iterator<Entry<Long, Book>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            final Entry<Long, Book> cursor = iterator.next();
            BookResource.LOGGER.debug(cursor.getKey());
            bookList.add(cursor.getValue());
        }
        final Books books = new Books(bookList);
        BookResource.LOGGER.debug(books);
        return books;
    }

    @Path("{bookId:[0-9]*}")
    @GET
    public Book getBookByPath(@PathParam("bookId") final Long bookId) {
        final Book book = BookResource.memoryBase.get(bookId);
        BookResource.LOGGER.debug(book);
        return book;
    }

    @Path("/book")
    @GET
    public Book getBookByQuery(@QueryParam("id") final Long bookId) {
        final Book book = BookResource.memoryBase.get(bookId);
        BookResource.LOGGER.debug(book);
        return book;
    }

    @POST
    public Book saveBook(final Book book) {
        book.setBookId(System.nanoTime());
        BookResource.memoryBase.put(book.getBookId(), book);
        return book;
    }
}
