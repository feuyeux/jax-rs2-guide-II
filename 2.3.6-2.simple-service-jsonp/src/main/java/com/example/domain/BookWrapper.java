package com.example.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class BookWrapper implements Serializable {
    private static final long serialVersionUID = 1L;

    private Book book;
    private String link;

    public BookWrapper() {

    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @XmlAttribute(name = "bookId")
    public Long getBookId() {
        return book.getBookId();
    }

    @XmlAttribute(name = "bookName")
    public String getBookName() {
        return book.getBookName();
    }

    @XmlAttribute(name = "publisher")
    public String getPublisher() {
        return book.getPublisher();
    }

    @XmlAttribute(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return book + ":" + link;
    }
}
