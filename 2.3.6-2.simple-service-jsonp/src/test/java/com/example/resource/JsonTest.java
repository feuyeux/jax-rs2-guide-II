package com.example.resource;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

public class JsonTest extends JerseyTest {
    private final static Logger LOGGER = Logger.getLogger(JsonTest.class);

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(BookResource.class);
    }

    @Test
    public void testSaveBook() {
        JsonObject newBook = Json.createObjectBuilder().add("bookName", "Java EE 7 精髓").add("publisher", "人邮").build();
        Entity<JsonObject> entity = Entity.entity(newBook, MediaType.APPLICATION_JSON_TYPE);
        JsonObject book = target("books").request(MediaType.APPLICATION_JSON_TYPE).post(entity, JsonObject.class);
        LOGGER.debug(book.getJsonNumber("bookId") + "\t" + book.getString("bookName"));
    }

    @Test
    public void testGetBook() {
        WebTarget booksTarget = target("books").path("book").queryParam("id", 2);
        JsonObject book = booksTarget.request(MediaType.APPLICATION_JSON_TYPE).get(JsonObject.class);
        LOGGER.debug(book.getJsonNumber("bookId") + "\t" + book.getString("bookName"));
    }

    @Test
    public void testGetBooks() {
        JsonArray books = target("books").request(MediaType.APPLICATION_JSON_TYPE).get(JsonArray.class);
        for (JsonValue jsonValue : books) {
            JsonObject book = (JsonObject) jsonValue;
            LOGGER.debug(book.getJsonNumber("bookId") + "\t" + book.getString("bookName"));
        }
    }
}
