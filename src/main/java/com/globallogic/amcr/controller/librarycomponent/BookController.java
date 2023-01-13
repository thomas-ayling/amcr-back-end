package com.globallogic.amcr.controller.librarycomponent;

import java.util.List;
import java.util.UUID;

import com.globallogic.amcr.persistence.model.librarycomponent.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.globallogic.amcr.persistence.payload.librarycomponent.BookResponse;
import com.globallogic.amcr.service.librarycomponent.BookServiceImpl;

@RestController
@RequestMapping("/library")
@CrossOrigin
public class BookController {
    private final BookServiceImpl bookServiceImpl;
    public final Logger Log = LoggerFactory.getLogger(BookController.class.getName());

    public BookController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<Book> getMany() {
        try {
            return bookServiceImpl.getAll();
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the BookController  - could not retrieve all books", e);
        }

    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Book get(@PathVariable UUID id) {
        try {
            return bookServiceImpl.get(id);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the BookController  - could not retrieve all books", e);
        }

    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes="application/json")
    public ResponseEntity<Book> reserve(@PathVariable UUID id, @RequestBody Book reservedBook) {
        try {
            Log.debug("Controller requesting deletion of book with ID {}", id);
            bookServiceImpl.reserve(id, reservedBook);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the BookController  - could not reserve book", e);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> saveBook(@RequestBody @Validated Book book, BindingResult errors) {
        if (errors.hasErrors()) {
            System.out.println(errors.toString());
        }
        try {
            Book returnedBook = bookServiceImpl.save(book);
            return ResponseEntity.ok().body(returnedBook);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the BookController  - could not save book");
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            Log.debug("Controller requesting deletion of book with ID {}", id);
            bookServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("There was an error in the BookController - could not delete book with ID " + id);
        }
    }

}
