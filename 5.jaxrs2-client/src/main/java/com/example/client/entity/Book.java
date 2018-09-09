package com.example.client.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int NAME_LENGTH = 100;
    private Long bookId;
    private String bookName;
    private String publisher;

    public Book() {
    }

    public Book(Long bookId) {
        this.bookId = bookId;
    }

    public Book(String bookName) {
        this.bookName = bookName;
    }

    public Book(Long bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public Book(Long bookId, String bookName, String publisher) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.publisher = publisher;
    }

    @XmlAttribute(
            name = "bookId"
    )
    public Long getBookId() {
        return this.bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @XmlAttribute(
            name = "bookName"
    )
    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @XmlAttribute(
            name = "publisher"
    )
    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String toString() {
        return this.bookId + ":" + this.bookName + ":" + this.publisher;
    }
}