package com.example.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonHybridBook {
    @JsonProperty("bookId")
    private String bookId;

    @JsonProperty("bookName")
    private String bookName;

    public JsonHybridBook() {
        bookId = "2";
        bookName = "Java Restful Web Services实战";
    }
}
