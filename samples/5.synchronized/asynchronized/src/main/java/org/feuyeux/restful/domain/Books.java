package org.feuyeux.restful.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "books")
public class Books implements Serializable {
    private List<Book> bookList;

    public Books() {
        bookList = new ArrayList<>();
    }

    public Books(final List<Book> bookList) {
        super();
        this.bookList = bookList;
    }

    @XmlElement(name = "book")
    @XmlElementWrapper
    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(final List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "{" + bookList + "}";
    }
}
