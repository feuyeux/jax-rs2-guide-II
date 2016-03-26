package com.example.resource;

import com.example.domain.Book;
import com.example.domain.Books;
import com.example.jackson.JsonBook;
import com.example.jackson.JsonHybridBook;
import com.example.jackson.JsonNoJaxbBook;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.Map.Entry;

@Path("books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
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

    @Path("/emptybook")
    @GET
    public JsonBook getEmptyArrayBook() {
        final JsonBook book = new JsonBook();
        BookResource.LOGGER.debug(book);
        return book;
    }

    @Path("/hybirdbook")
    @GET
    public JsonHybridBook getHybirdBook() {
        final JsonHybridBook book = new JsonHybridBook();
        BookResource.LOGGER.debug(book);
        return book;
    }

    @Path("/nojaxbbook")
    @GET
    public JsonNoJaxbBook getNoJaxbBook() {
        final JsonNoJaxbBook book = new JsonNoJaxbBook();
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

    @POST
    public Book saveBook(final Book book) {
        book.setBookId(System.nanoTime());
        BookResource.memoryBase.put(book.getBookId(), book);
        return book;
    }
}
