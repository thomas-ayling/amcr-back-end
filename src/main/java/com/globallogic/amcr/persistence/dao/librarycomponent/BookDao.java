package com.globallogic.amcr.persistence.dao.librarycomponent;

import java.util.List;
import java.util.UUID;

import com.globallogic.amcr.persistence.dao.Dao;
import com.globallogic.amcr.persistence.model.librarycomponent.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.globallogic.amcr.persistence.mapper.librarycomponent.BookMapper;

@Repository
public class BookDao implements Dao<Book, Book> {
    public static final Logger Log = LoggerFactory.getLogger(BookDao.class.getName());
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

    public Book get(UUID id) {
        Log.trace("DAO requesting book with ID {}", id);
        return bookMapper.get(id);
    }

    public List<Book> getAll() {
        Log.trace("DAO requesting all books");
        return bookMapper.getAll();
    }

    public Book reserve(UUID id, Book oldBook, Book reservedBook) {
        reservedBook.setId(id);
        if (oldBook.equals(reservedBook)) {
            return reservedBook;
        }
        if (reservedBook.getTitle() == null) {
            reservedBook.setTitle(oldBook.getTitle());
        }
        if (reservedBook.getAuthor() == null) {
            reservedBook.setAuthor(oldBook.getAuthor());
        }
        if (reservedBook.getGenre() == null) {
            reservedBook.setGenre(oldBook.getGenre());
        }
        if (reservedBook.getCover() == null) {
            reservedBook.setCover(oldBook.getCover());
        }
        if (reservedBook.getTitle() == null) {
            reservedBook.setTitle(oldBook.getTitle());
        }
        if (reservedBook.getTitle() == null) {
            reservedBook.setTitle(oldBook.getTitle());
        }
            Log.trace("DAO reserving book with ID {} and Content: \n{}\n\n\n\n with new book:\n\n{}", id , oldBook, reservedBook);
            bookMapper.reserve(id, reservedBook);
            return reservedBook;
    }
    public void delete(UUID id) {
        try {
            Log.trace("DAO deleting book with ID {}", id);
            bookMapper.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Error in BookDao - could not delete book with ID {}" + id, e);
        }
    }
}
