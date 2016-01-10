package com.example.conneg;

import com.example.domain.Book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.HashMap;

@Path("conneg-resource")
public class ConnegResource {


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Book getJaxbBook(@PathParam("id") final Long bookId) {
        return new Book(bookId);
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getJsonBook(@PathParam("id") final Long bookId) {
        return new Book(bookId);
    }


    @GET
    @Produces({"application/json; qs=.9", "application/xml; qs=.5"})
    @Path("book/{id}")
    public Book getBook(@PathParam("id") final Long bookId) {
        return new Book(bookId);
    }


    private static HashMap<String, String> helloMap = new HashMap<>();

    static {
        helloMap.put("ja", "おはようございます");
        helloMap.put("fr", "bonjour");
        helloMap.put("es", "hola");
        helloMap.put("zh-cn", "你好");
    }

    @GET
    @Produces("text/*")
    public String getHello(@Context HttpHeaders headers) {
        final MultivaluedMap<String, String> headerMap = headers.getRequestHeaders();
        //simple way to presentation, maybe, more values
        String lang = headerMap.get("content-language").get(0);
        String hello = helloMap.get(lang);
        if (hello != null) {
            return hello;
        } else {
            return "Hello";
        }
    }
}
