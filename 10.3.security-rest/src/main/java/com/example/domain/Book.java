package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>Book class.</p>
 *
 * @author hanl
 * @version $Id: $Id
 */
@Entity
@Table(name = "simple_book")
@XmlRootElement
public class Book implements Serializable {
    private static final long serialVersionUID = 3749670031307574543L;
    private Integer bookId;
    private String bookName;
    private String publisher;
    private static final int NAME_LENGTH = 100;

    /**
     * <p>Constructor for Book.</p>
     */
    public Book() {
        super();
    }

    /**
     * <p>Constructor for Book.</p>
     *
     * @param bookId a {@link java.lang.Integer} object.
     */
    public Book(final Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * <p>Constructor for Book.</p>
     *
     * @param bookName a {@link java.lang.String} object.
     */
    public Book(final String bookName) {
        this.bookName = bookName;
    }

    /**
     * <p>Constructor for Book.</p>
     *
     * @param bookId a {@link java.lang.Integer} object.
     * @param bookName a {@link java.lang.String} object.
     */
    public Book(final Integer bookId, final String bookName) {
        super();
        this.bookId = bookId;
        this.bookName = bookName;
    }

    /**
     * <p>Getter for the field <code>bookId</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "EMP_SEQ")
    @SequenceGenerator(name = "EMP_SEQ")
    @Column(unique = true, nullable = false, name = "BOOKID")
    @XmlAttribute(name = "bookId")
    public Integer getBookId() {
        return bookId;
    }

    /**
     * <p>Setter for the field <code>bookId</code>.</p>
     *
     * @param bookId a {@link java.lang.Integer} object.
     */
    public void setBookId(final Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * <p>Getter for the field <code>bookName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    @Column(length = Book.NAME_LENGTH, name = "BOOKNAME")
    @XmlAttribute(name = "bookName")
    public String getBookName() {
        return bookName;
    }

    /**
     * <p>Setter for the field <code>bookName</code>.</p>
     *
     * @param bookName a {@link java.lang.String} object.
     */
    public void setBookName(final String bookName) {
        this.bookName = bookName;
    }

    /**
     * <p>Getter for the field <code>publisher</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    @Column(length = Book.NAME_LENGTH, name = "PUBLISHER")
    @XmlAttribute(name = "publisher")
    public String getPublisher() {
        return publisher;
    }

    /**
     * <p>Setter for the field <code>publisher</code>.</p>
     *
     * @param publisher a {@link java.lang.String} object.
     */
    public void setPublisher(final String publisher) {
        this.publisher = publisher;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return bookId + ":" + bookName + ":" + publisher;
    }
}
