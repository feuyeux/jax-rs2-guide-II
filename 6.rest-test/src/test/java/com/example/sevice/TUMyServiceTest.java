package com.example.sevice;

import com.example.domain.Book;
import com.example.domain.Books;
import com.example.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TUMyServiceTest {
    @Autowired
    private BookService bookService;

    @Test
    public void testGetAndSave() {
        final Book result = bookService.getBook(1L);
        if (result == null) {
            final Book newBook = new Book("Java Restful Web Service实战");
            final Book result0 = bookService.saveBook(newBook);
            Assert.assertNotNull(result0.getBookId());
        } else {
            Assert.assertNotNull(result.getBookId());
        }
    }

    @Test
    public void testFindAll() {
        final Books result0 = bookService.getBooks();
        final List<Book> bookList = result0.getBookList();
        if (bookList != null) {
            for (final Book book : bookList) {
                Assert.assertNotNull(book.getBookId());
            }
        }
    }
}
