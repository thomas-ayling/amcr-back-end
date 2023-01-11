package com.globallogic.amcr.service.librarycomponent;

import java.util.List;
import java.util.UUID;

import com.globallogic.amcr.persistence.model.librarycomponent.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globallogic.amcr.persistence.dao.librarycomponent.BookDao;
import com.globallogic.amcr.persistence.payload.librarycomponent.BookResponse;

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
    public List<BookResponse> getAll() {
        return bookDao.getAll();
    }

    @Transactional(readOnly = true)
    public BookResponse get(UUID id) {
        return bookDao.get(id);
    }

}
