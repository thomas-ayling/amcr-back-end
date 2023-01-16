package com.globallogic.amcr.controller.librarycomponent;

import java.util.List;
import java.util.UUID;

import com.globallogic.amcr.exception.contactcomponent.NotFoundException;
import com.globallogic.amcr.persistence.model.librarycomponent.Book;
import com.globallogic.amcr.service.librarycomponent.BookService;
import com.globallogic.amcr.utils.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/library")
@CrossOrigin
public class BookController {
    private final BookService bookService;
    private final Logger LOG = LoggerFactory.getLogger(BookController.class.getName());

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<Book> getMany() {
        try {
            LOG.debug("Controller requesting all books");
            return bookService.getAll();
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the BookController  - could not retrieve all books", e);
        }

    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Book get(@PathVariable UUID id) {
        Assert.assertNull(id, "Id cannot be null");
        try {
            LOG.debug("Controller requesting book with ID {}", id);
            return bookService.get(id);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the BookController  - could not retrieve all books", e);
        }

    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes="application/json")
    public ResponseEntity<Book> reserve(@PathVariable UUID id, @RequestBody Book reservedBook) {
        Assert.assertNull(id, "Reservation Id cannot be null");
        Assert.assertNull(reservedBook, "Reservation information cannot be null");
        try {
            LOG.debug("Controller requesting deletion of book with ID {}", id);
            bookService.reserve(id, reservedBook);
            return ResponseEntity.ok().body(reservedBook);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the BookController  - could not reserve book", e);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Book> saveBook(@RequestBody @Validated Book book, BindingResult errors) {
        Assert.assertNull(book, "Book information object cannot be null");
        if (errors.hasErrors()) {
                throw new NotFoundException("error in the book controller while saving a new book");
        }
        try {
            LOG.debug("Controller requesting a new book to be saved with title {}", book.getTitle());
            Book returnedBook = bookService.save(book);
            return ResponseEntity.accepted().body(returnedBook);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the BookController  - could not save book", e);
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        Assert.assertNull(id, "Deletion reservation ID cannot be null");
        try {
            LOG.debug("Controller requesting deletion of book with ID {}", id);
            bookService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the BookController - could not delete book with ID " + id, e);
        }
    }

}
