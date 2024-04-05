package com.example.service;

import com.example.dao.BookDao;
import com.example.domain.Book;
import com.example.domain.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * <p>BookService class.</p>
 *
 * @author hanl
 * @version $Id: $Id
 */
public class BookService {
    @Autowired
    private BookDao bookDao;

    /**
     * <p>Constructor for BookService.</p>
     */
    public BookService() {
    }

    /**
     * <p>saveBook.</p>
     *
     * @param book a {@link com.example.domain.Book} object.
     * @return a {@link com.example.domain.Book} object.
     */
    public Book saveBook(final Book book) {
        return bookDao.store(book);
    }

    /**
     * <p>getBook.</p>
     *
     * @param bookId a {@link java.lang.Integer} object.
     * @return a {@link com.example.domain.Book} object.
     */
    public Book getBook(final Integer bookId) {
        try {
            return bookDao.findById(bookId);
        } catch (final Exception e) {
            return new Book(-1, "");
        }
    }

    /**
     * <p>getBooks.</p>
     *
     * @return a {@link com.example.domain.Books} object.
     */
    public Books getBooks() {
        return new Books(bookDao.findAll());
    }

    /**
     * <p>updateBook.</p>
     *
     * @param bookId a {@link java.lang.Integer} object.
     * @param book   a {@link com.example.domain.Book} object.
     * @return a {@link com.example.domain.Book} object.
     */
    public Book updateBook(final Integer bookId, final Book book) {
        book.setBookId(bookId);
        bookDao.update(book);
        return book;
    }

    /**
     * <p>deleteBook.</p>
     *
     * @param bookId a {@link java.lang.Integer} object.
     * @return a boolean.
     */
    public boolean deleteBook(final Integer bookId) {
        return bookDao.remove(bookId);
    }
}
