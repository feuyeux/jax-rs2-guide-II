package com.example.domain;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Books class.
 *
 * @author hanl
 * @version $Id: $Id
 */
@XmlRootElement(name = "books")
public class Books implements Serializable {
  private static final long serialVersionUID = -5070487415443208853L;
  private List<Book> bookList;

  /** Constructor for Books. */
  public Books() {
    super();
  }

  /**
   * Constructor for Books.
   *
   * @param bookList a {@link List} object.
   */
  public Books(final List<Book> bookList) {
    super();
    this.bookList = bookList;
  }

  /**
   * Getter for the field <code>bookList</code>.
   *
   * @return a {@link List} object.
   */
  @XmlElement(name = "book")
  @XmlElementWrapper
  public List<Book> getBookList() {
    return bookList;
  }

  /**
   * Setter for the field <code>bookList</code>.
   *
   * @param bookList a {@link List} object.
   */
  public void setBookList(final List<Book> bookList) {
    this.bookList = bookList;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "{" + bookList + "}";
  }
}
