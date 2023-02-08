package com.globallogic.amcr.repository.impl.librarycomponent;

import java.util.List;
import java.util.UUID;

import com.globallogic.amcr.model.librarycomponent.Book;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class BookDaoImpl implements com.globallogic.amcr.repository.librarycomponent.BookDao {
    private final Logger LOG = LoggerFactory.getLogger(BookDaoImpl.class);
    private final BookMapper bookMapper;

    public BookDaoImpl(BookMapper bookMapper) {
        this.bookMapper = Assert.assertNotNull(bookMapper, "Id cannot be null to request");
    }

    @Override
    public Book save(Book book, UUID id) {
        try {
            book.setId(id);
            bookMapper.save(book);
            return book;
        } catch (Exception e) {
            throw new RuntimeException("DAO could not save book", e);
        }
    }

    @Override
    public Book get(UUID id) {
        LOG.trace("DAO requesting book with ID {}", id);
        return bookMapper.get(id);
    }

    @Override
    public List<Book> getAll() {
        LOG.trace("DAO requesting all books");
        return bookMapper.getAll();
    }

    @Override
    public Book update(UUID id, Book oldBook, Book reservedBook) {
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
        LOG.trace("DAO reserving book with ID {} and Content: \n{}\n\n\n\n with new book:\n\n{}", id , oldBook, reservedBook);
        bookMapper.reserve(id, reservedBook);
        return reservedBook;
    }

    @Override
    public void delete(UUID id) {
        try {
            LOG.trace("DAO deleting book with ID {}", id);
            bookMapper.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Error in BookDaoImpl - could not delete book with ID {}" + id, e);
        }
    }
}
