package com.example.resource;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonHybridBook {
    @SuppressWarnings("unused")
    @JsonProperty("bookId")
    private String bookId;

    @SuppressWarnings("unused")
    @JsonProperty("bookName")
    private String bookName;

    public JsonHybridBook() {
        bookId = "2";
        bookName = "Java Restful Web Services实战";
    }
}
