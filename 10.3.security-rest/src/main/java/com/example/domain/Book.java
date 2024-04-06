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
 * Book class.
 *
 * @author hanl
 * @version $Id: $Id
 */
@Entity
@Table(name = "simple_book")
@XmlRootElement
public class Book implements Serializable {
  private static final long serialVersionUID = 3749670031307574543L;
  private static final int NAME_LENGTH = 100;
  private Integer bookId;
  private String bookName;
  private String publisher;

  /** Constructor for Book. */
  public Book() {
    super();
  }

  /**
   * Constructor for Book.
   *
   * @param bookId a {@link java.lang.Integer} object.
   */
  public Book(final Integer bookId) {
    this.bookId = bookId;
  }

  /**
   * Constructor for Book.
   *
   * @param bookName a {@link java.lang.String} object.
   */
  public Book(final String bookName) {
    this.bookName = bookName;
  }

  /**
   * Constructor for Book.
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
   * Getter for the field <code>bookId</code>.
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
   * Setter for the field <code>bookId</code>.
   *
   * @param bookId a {@link java.lang.Integer} object.
   */
  public void setBookId(final Integer bookId) {
    this.bookId = bookId;
  }

  /**
   * Getter for the field <code>bookName</code>.
   *
   * @return a {@link java.lang.String} object.
   */
  @Column(length = Book.NAME_LENGTH, name = "BOOKNAME")
  @XmlAttribute(name = "bookName")
  public String getBookName() {
    return bookName;
  }

  /**
   * Setter for the field <code>bookName</code>.
   *
   * @param bookName a {@link java.lang.String} object.
   */
  public void setBookName(final String bookName) {
    this.bookName = bookName;
  }

  /**
   * Getter for the field <code>publisher</code>.
   *
   * @return a {@link java.lang.String} object.
   */
  @Column(length = Book.NAME_LENGTH, name = "PUBLISHER")
  @XmlAttribute(name = "publisher")
  public String getPublisher() {
    return publisher;
  }

  /**
   * Setter for the field <code>publisher</code>.
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
