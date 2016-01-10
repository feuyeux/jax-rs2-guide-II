package com.example.media.xml;

import com.example.domain.Book;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;

@Path("xml-resource")
public class XMLResource {
    private static final Logger LOGGER = Logger.getLogger(XMLResource.class);

    @POST
    @Path("stream")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public StreamSource getStreamSource(javax.xml.transform.stream.StreamSource streamSource) {
        return streamSource;
    }

    @POST
    @Path("sax")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public SAXSource getSAXSource(javax.xml.transform.sax.SAXSource saxSource) {
        return saxSource;
    }

    @POST
    @Path("dom")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public DOMSource getDOMSource(javax.xml.transform.dom.DOMSource domSource) {
        return domSource;
    }

    @POST
    @Path("doc")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Document getDocument(org.w3c.dom.Document document) {
        return document;
    }

    @POST
    @Path("jaxb")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Book getEntity(JAXBElement<Book> bookElement) {
        Book book = bookElement.getValue();
        LOGGER.debug(book.getBookName());
        return book;
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_XML)
    public Book getEntity(Book book) {
        LOGGER.debug(book.getBookName());
        return book;
    }
}
