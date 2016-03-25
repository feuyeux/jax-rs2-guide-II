package com.example.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "simple_book")
@XmlRootElement
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int NAME_LENGTH = 100;

    private Long bookId;
    private String bookName;
    private String publisher;

    public Book() {
        super();
    }

    public Book(final Long bookId) {
        this.bookId = bookId;
    }

    public Book(final String bookName) {
        this.bookName = bookName;
    }

    public Book(final Long bookId, final String bookName) {
        super();
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public Book(final Long bookId, final String bookName, String publisher) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.publisher = publisher;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "EMP_SEQ")
    @SequenceGenerator(name = "EMP_SEQ")
    @Column(unique = true, nullable = false, name = "BOOKID")
    @XmlAttribute(name = "bookId")
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(final Long bookId) {
        this.bookId = bookId;
    }

    @Column(length = Book.NAME_LENGTH, name = "BOOKNAME")
    @XmlAttribute(name = "bookName")
    public String getBookName() {
        return bookName;
    }

    public void setBookName(final String bookName) {
        this.bookName = bookName;
    }

    @Column(length = Book.NAME_LENGTH, name = "PUBLISHER")
    @XmlAttribute(name = "publisher")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(final String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return bookId + ":" + bookName + ":" + publisher;
    }
}
