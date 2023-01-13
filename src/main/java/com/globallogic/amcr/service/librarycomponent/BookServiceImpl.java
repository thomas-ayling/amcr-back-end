package com.globallogic.amcr.service.librarycomponent;

import java.util.List;
import java.util.UUID;

import com.globallogic.amcr.persistence.model.librarycomponent.Book;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globallogic.amcr.persistence.dao.librarycomponent.BookDao;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    public final Logger Log = LoggerFactory.getLogger(BookServiceImpl.class.getName());

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Transactional
    public Book save(Book book) {
        UUID id = UUID.randomUUID();
        Log.debug("Service saving book with id {}:\n {}", id, book);

        try {
            return bookDao.save(book, id);
        } catch (Exception e) {
            throw new RuntimeException("Error in library service - could not save library object", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Book> getAll() {
        Log.debug("Service requesting all books");
        return bookDao.getAll();
    }

    @Transactional(readOnly = true)
    public Book get(UUID id) {
        Assert.assertNull(id, "Id cannot be null to request");
        Log.debug("Service saving new book");
        return bookDao.get(id);
    }

    @Transactional
    public Book reserve(UUID id, Book reservedBook) {
        Assert.assertNull(id, "ID must be included to reserve book");
        Assert.assertNull(reservedBook, "New reservation cannot be empty");
        Book oldbook = Assert.assertNull(bookDao.get(id), "Objects with specified ID could not be found. Try again");
        Log.debug("Service requesting reservation of book with ID {}", id);
        return bookDao.reserve(id, oldbook, reservedBook);
    }

    @Transactional
    public void delete(UUID id) {
        Assert.assertNull(id, "ID cannot be null to delete a book");
        Log.debug("Service requesting deletion of book with ID {}", id);
        bookDao.delete(id);}
}
