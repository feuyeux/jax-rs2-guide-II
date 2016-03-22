package com.example.sevice;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.domain.Book;
import com.example.domain.Books;
import com.example.service.BookService;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TUMyServiceTest {
    private final static Logger LOGGER = Logger.getLogger(TUMyServiceTest.class);
    @Autowired
    private BookService bookService;

    @Test
    public void testGetAndSave() {
        final Book result = bookService.getBook(1L);
        if (result == null) {
            final Book newBook = new Book("Java Restful Web Service实战");
            final Book result0 = bookService.saveBook(newBook);
            TUMyServiceTest.LOGGER.debug(result0);
            Assert.assertNotNull(result0.getBookId());
        } else {
            TUMyServiceTest.LOGGER.debug(result);
            Assert.assertNotNull(result.getBookId());
        }
    }

    @Test
    public void testFindAll() {
        final Books result0 = bookService.getBooks();
        final List<Book> bookList = result0.getBookList();
        if (bookList != null) {
            for (final Book book : bookList) {
                TUMyServiceTest.LOGGER.debug(book);
                Assert.assertNotNull(book.getBookId());
            }
        }
    }
}
