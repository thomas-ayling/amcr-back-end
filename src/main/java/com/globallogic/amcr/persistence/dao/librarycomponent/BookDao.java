package com.globallogic.amcr.persistence.dao.librarycomponent;

import java.util.List;
import java.util.UUID;

import com.globallogic.amcr.persistence.dao.Dao;
import com.globallogic.amcr.persistence.model.librarycomponent.Book;
import org.springframework.stereotype.Repository;

import com.globallogic.amcr.mapper.librarycomponent.BookMapper;
import com.globallogic.amcr.persistence.payload.librarycomponent.BookResponse;

@Repository
public class BookDao implements Dao<Book, BookResponse> {
    final BookMapper bookMapper;

    public BookDao(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public Book save(Book book, UUID id) {
        try {
            book.setId(id);
            bookMapper.save(book);
            return book;
        } catch (Exception e) {
            throw new RuntimeException("DAO could not save book", e);
        }
    }
    
    public BookResponse get(UUID id) {
        return bookMapper.get(id);
    }

    public List<BookResponse> getAll() {
        return bookMapper.getAll();
    }
}
