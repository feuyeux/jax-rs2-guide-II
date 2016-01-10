package com.example.resource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

@Path("books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {
    private static final Logger LOGGER = Logger.getLogger(BookResource.class);
    private static final HashMap<Long, JsonObject> memoryBase;
    static {
        memoryBase = com.google.common.collect.Maps.newHashMap();
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

        JsonObject newBook1 = jsonObjectBuilder.add("bookId", 1).add("bookName", "JSF和Richfaces实战").add("publisher", "电子·博文").build();
        JsonObject newBook2 = jsonObjectBuilder.add("bookId", 2).add("bookName", "Java Restful Web Services实战").add("publisher", "机工·华章").build();
        memoryBase.put(1L, newBook1);
        memoryBase.put(2L, newBook2);
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
