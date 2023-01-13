package com.globallogic.amcr.service.librarycomponent;

import java.util.List;
import java.util.UUID;

import com.globallogic.amcr.persistence.model.librarycomponent.Book;

import com.globallogic.amcr.persistence.payload.librarycomponent.BookResponse;

public interface BookService {

    Book save(Book book);

    Book get(UUID id);

    List<Book> getAll();

    Book reserve(UUID id, Book reservedBook);

    void delete(UUID id);
    
}
