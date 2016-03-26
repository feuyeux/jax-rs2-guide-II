package com.example.resource;

import com.example.jackson.JsonHybridBook;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonContextProvider implements ContextResolver<ObjectMapper> {
    final ObjectMapper d;
    final ObjectMapper c;

    public JsonContextProvider() {
        d = createDefaultMapper();
        c = createCombinedMapper();
    }

    private static ObjectMapper createCombinedMapper() {
        return new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, true)
                .configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true)
                .setAnnotationIntrospector(createIntrospector());
    }

    private static ObjectMapper createDefaultMapper() {
        ObjectMapper result = new ObjectMapper();
        result.enable(SerializationFeature.INDENT_OUTPUT);
        return result;
    }

    private static AnnotationIntrospector createIntrospector() {
        AnnotationIntrospector p = new JacksonAnnotationIntrospector();
        AnnotationIntrospector s = new JaxbAnnotationIntrospector();
        return AnnotationIntrospector.pair(p, s);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        if (type == JsonHybridBook.class) {
            return c;
        } else {
            return d;
        }
    }
}
