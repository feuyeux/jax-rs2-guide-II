package com.example.link;

import com.example.domain.Book;

import java.util.HashMap;

public class LinkCache {

    final static HashMap<Long, Book> map;

    static {
        map = new HashMap<>();
        LinkCache.map.put(1000L, new Book(1000L, "上经"));
        LinkCache.map.put(1001L, new Book(1001L, "下经"));
        LinkCache.map.put(1002L, new Book(1002L, "彖传"));
        LinkCache.map.put(1003L, new Book(1003L, "象传"));
        LinkCache.map.put(1004L, new Book(1004L, "文言"));
        LinkCache.map.put(1005L, new Book(1005L, "系辞传"));
        LinkCache.map.put(1006L, new Book(1006L, "说卦传"));
        LinkCache.map.put(1007L, new Book(1007L, "序卦传"));
        LinkCache.map.put(1008L, new Book(1008L, "杂卦传"));
    }
}
