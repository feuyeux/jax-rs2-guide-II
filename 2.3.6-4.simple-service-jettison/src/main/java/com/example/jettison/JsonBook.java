package com.example.jettison;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonBook {
    private String bookId;
    private String bookName;

    public JsonBook() {
        bookId = "1";
        bookName = "Java Restful Web Services实战";
    }

    @XmlElement
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @XmlElement
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
