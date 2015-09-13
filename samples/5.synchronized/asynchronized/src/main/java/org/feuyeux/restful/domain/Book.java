package org.feuyeux.restful.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Book implements Serializable {
    private Long bookId;
    private String bookName;
    private String publisher;

    public Book() {
    }

    public Book(final long bookId, final String bookName, String publisher) {
        this.bookId = Long.valueOf(bookId);
        this.bookName = bookName;
        this.publisher = publisher;
    }

    @XmlAttribute(name = "bookId")
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(final Long bookId) {
        this.bookId = bookId;
    }

    @XmlAttribute(name = "bookName")
    public String getBookName() {
        return bookName;
    }

    public void setBookName(final String bookName) {
        this.bookName = bookName;
    }

    @XmlAttribute(name = "publisher")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(final String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(getBookId()).append(",")
                .append(getBookName()).append(",")
                .append(getPublisher());
        return sb.toString();
    }
}
