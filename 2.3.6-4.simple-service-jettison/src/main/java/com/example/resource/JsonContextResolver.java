package com.example.resource;

import com.example.domain.Book;
import com.example.domain.Books;
import com.example.jettison.JsonBook;
import com.example.jettison.JsonBook2;
import org.glassfish.jersey.jettison.JettisonConfig;
import org.glassfish.jersey.jettison.JettisonJaxbContext;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

@Provider
public class JsonContextResolver implements ContextResolver<JAXBContext> {
    private final JAXBContext context1;
    private final JAXBContext context2;

    @SuppressWarnings("rawtypes")
    public JsonContextResolver() throws Exception {
        Class[] clz = new Class[]{JsonBook.class, JsonBook2.class, Books.class, Book.class};
        this.context1 = new JettisonJaxbContext(JettisonConfig.DEFAULT, clz);
        this.context2 = new JettisonJaxbContext(JettisonConfig.badgerFish().build(), clz);
    }

    @Override
    public JAXBContext getContext(Class<?> objectType) {
        if (objectType == JsonBook2.class) {
            return context2;
        } else {
            return context1;
        }
    }
}