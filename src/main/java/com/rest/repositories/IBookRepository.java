package com.rest.repositories;

import com.rest.models.Book;

import java.util.List;

public interface IBookRepository {
    void create(Book book);

    List<Book> findByName(String name);
}
