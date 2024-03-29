package com.globallogic.amcr.service.impl.librarycomponent;

import java.util.List;
import java.util.UUID;

import com.globallogic.amcr.model.librarycomponent.Book;
import com.globallogic.amcr.repository.impl.librarycomponent.BookDaoImpl;
import com.globallogic.amcr.service.librarycomponent.BookService;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BookServiceImpl implements BookService {
    private final BookDaoImpl bookDaoImpl;
    private final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class.getName());

    public BookServiceImpl(BookDaoImpl bookDaoImpl) {
        this.bookDaoImpl = Assert.assertNotNull(bookDaoImpl, "Dao cannot be null to request");
    }

    @Transactional
    public Book save(Book book) {
        UUID id = UUID.randomUUID();
        LOG.debug("Service saving book with id {}:\n {}", id, book);
        return bookDaoImpl.save(book, id);
    }

    @Transactional(readOnly = true)
    public List<Book> getAll() {
        LOG.debug("Service requesting all books");
        return bookDaoImpl.getAll();
    }

    @Transactional(readOnly = true)
    public Book get(UUID id) {
        Assert.assertNotNull(id, "Id cannot be null to request");
        LOG.debug("Service saving new book");
        return bookDaoImpl.get(id);
    }

    @Transactional
    public Book reserve(UUID id, Book reservedBook) {
        Assert.assertNotNull(id, "ID must be included to reserve book");
        Assert.assertNotNull(reservedBook, "New reservation cannot be empty");
        Book oldbook = Assert.assertNotNull(bookDaoImpl.get(id), "Objects with specified ID could not be found. Try again");
        LOG.debug("Service requesting reservation of book with ID {}", id);
        return bookDaoImpl.update(id, oldbook, reservedBook);
    }

    @Transactional
    public void delete(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null to delete a book");
        LOG.debug("Service requesting deletion of book with ID {}", id);
        bookDaoImpl.delete(id);}
}
