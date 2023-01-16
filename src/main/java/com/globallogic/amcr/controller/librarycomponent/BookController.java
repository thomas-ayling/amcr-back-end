package com.globallogic.amcr.controller.librarycomponent;

import java.util.List;
import java.util.UUID;

import com.globallogic.amcr.exception.contactcomponent.NotFoundException;
import com.globallogic.amcr.persistence.model.librarycomponent.Book;
import com.globallogic.amcr.service.librarycomponent.BookService;
import com.globallogic.amcr.utils.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/books")
@CrossOrigin
public class BookController {
    private final BookService bookService;
    private final Logger LOG = LoggerFactory.getLogger(BookController.class.getName());

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<Book> getMany() {
            LOG.debug("Controller requesting all books");
            return bookService.getAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Book get(@PathVariable UUID id) {
        Assert.assertNotNull(id, "Id cannot be null");
            LOG.debug("Controller requesting book with ID {}", id);
            return bookService.get(id);
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes="application/json")
    public ResponseEntity<Book> reserve(@PathVariable UUID id, @RequestBody Book reservedBook) {
        Assert.assertNotNull(id, "Reservation Id cannot be null");
        Assert.assertNotNull(reservedBook, "Reservation information cannot be null");
        LOG.debug("Controller requesting deletion of book with ID {}", id);
        bookService.reserve(id, reservedBook);
        return ResponseEntity.ok().body(reservedBook);
    }

    @PostMapping("/")
    public ResponseEntity<Book> saveBook(@RequestBody @Validated Book book, BindingResult errors) {
        Assert.assertNotNull(book, "Book information object cannot be null");
        if (errors.hasErrors()) {
            throw new NotFoundException(errors.toString());
        }
            LOG.debug("Controller requesting a new book to be saved with title {}", book.getTitle());
            Book returnedBook = bookService.save(book);
            return ResponseEntity.accepted().body(returnedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        Assert.assertNotNull(id, "Deletion reservation ID cannot be null");
        LOG.debug("Controller requesting deletion of book with ID {}", id);
        bookService.delete(id);
        return ResponseEntity.noContent().build();
        }

}
