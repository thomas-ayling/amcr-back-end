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
    private final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class.getName());

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = Assert.assertNotNull(bookDao, "Dao cannot be null to request");
    }

    @Transactional
    public Book save(Book book) {
        UUID id = UUID.randomUUID();
        LOG.debug("Service saving book with id {}:\n {}", id, book);
        return bookDao.save(book, id);
    }

    @Transactional(readOnly = true)
    public List<Book> getAll() {
        LOG.debug("Service requesting all books");
        return bookDao.getAll();
    }

    @Transactional(readOnly = true)
    public Book get(UUID id) {
        Assert.assertNotNull(id, "Id cannot be null to request");
        LOG.debug("Service saving new book");
        return bookDao.get(id);
    }

    @Transactional
    public Book reserve(UUID id, Book reservedBook) {
        Assert.assertNotNull(id, "ID must be included to reserve book");
        Assert.assertNotNull(reservedBook, "New reservation cannot be empty");
        Book oldbook = Assert.assertNotNull(bookDao.get(id), "Objects with specified ID could not be found. Try again");
        LOG.debug("Service requesting reservation of book with ID {}", id);
        return bookDao.reserve(id, oldbook, reservedBook);
    }

    @Transactional
    public void delete(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null to delete a book");
        LOG.debug("Service requesting deletion of book with ID {}", id);
        bookDao.delete(id);}
}
