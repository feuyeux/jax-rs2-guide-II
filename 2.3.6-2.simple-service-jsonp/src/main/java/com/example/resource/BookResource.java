package com.example.resource;

import org.apache.log4j.Logger;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Path("books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {
    private static final Logger LOGGER = Logger.getLogger(BookResource.class);
    private static final HashMap<Long, JsonObject> memoryBase;

    static {
        memoryBase = com.google.common.collect.Maps.newHashMap();
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        JsonObject newBook1 = jsonObjectBuilder.add("bookId", 1)
                .add("bookName", "Java Restful Web Services实战")
                .add("publisher", "机械工业出版社")
                .add("isbn", "9787111478881")
                .add("publishTime", "2014-09-01")
                .build();
        memoryBase.put(1L, newBook1);
        memoryBase.put(2L, jsonObjectBuilder.add("bookId", 2).add("bookName", "JSF2和RichFaces4使用指南")
                .add("publisher", "电子工业出版社").add("isbn", "9787121177378")
                .add("publishTime", "2012-09-01").build());
        memoryBase.put(3L, jsonObjectBuilder.add("bookId", 3).add("bookName", "Java EE 7 精髓")
                .add("publisher", "人民邮电出版社").add("isbn", "9787115375483")
                .add("publishTime", "2015-02-01").build());
        memoryBase.put(4L, jsonObjectBuilder.add("bookId", 4).add("bookName", "Java Restful Web Services实战II")
                .add("publisher", "机械工业出版社").build());
    }

    @GET
    public JsonArray getBooks() {
        final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        final Set<Map.Entry<Long, JsonObject>> entries = BookResource.memoryBase.entrySet();
        final Iterator<Entry<Long, JsonObject>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            final Entry<Long, JsonObject> cursor = iterator.next();
            Long key = cursor.getKey();
            JsonObject value = cursor.getValue();
            BookResource.LOGGER.debug(key);
            arrayBuilder.add(value);
        }
        JsonArray result = arrayBuilder.build();
        return result;
    }

    @Path("/book")
    @GET
    public JsonObject getBookByQuery(@QueryParam("id") final Long bookId) {
        final JsonObject book = BookResource.memoryBase.get(bookId);
        BookResource.LOGGER.debug(book);
        return book;
    }

    @POST
    public JsonObject saveBook(final JsonObject book) {
        long id = System.nanoTime();
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        JsonObject newBook = jsonObjectBuilder.add("bookId", id).add("bookName", book.get("bookName")).add("publisher", book.get("publisher")).build();
        BookResource.memoryBase.put(id, newBook);
        return newBook;
    }
}
