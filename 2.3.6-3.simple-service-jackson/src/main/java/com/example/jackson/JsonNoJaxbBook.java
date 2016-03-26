package com.example.jackson;

public class JsonNoJaxbBook {
    private String[] chapters;
    private String bookId;
    private String bookName;

    public JsonNoJaxbBook() {
        bookId = "3";
        bookName = "Java Restful Web Services实战";
        chapters = new String[0];
    }

    public String[] getChapters() {
        return chapters;
    }

    public void setChapters(String[] chapters) {
        this.chapters = chapters;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
