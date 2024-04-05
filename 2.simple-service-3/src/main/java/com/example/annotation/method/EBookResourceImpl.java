package com.example.annotation.method;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import com.example.domain.Book;
import com.example.domain.Books;

public class EBookResourceImpl implements BookResource {
    public static AtomicLong serverBookSequence = new AtomicLong();

    @Override
    public String getWeight() {
        return "150M";
    }

    @Override
    public String newBook(Book book) {
        SimpleDateFormat f = new SimpleDateFormat("d MMM yyyy HH:mm:ss");
        Date lastUpdate = Calendar.getInstance().getTime();
        //...

        return f.format(lastUpdate);
    }

    @Override
    public Book createBook(Book book) {
        book.setBookId(serverBookSequence.incrementAndGet());
        return book;
    }

    @Override
    public void delete(long bookId) {
       // LOGGER.debug(bookId);
    }

    @Override
    public boolean moveBooks(Books books) {
        //LOGGER.debug("MOVE method");
        return true;
    }
}
