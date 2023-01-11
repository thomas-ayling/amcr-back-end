package com.globallogic.amcr.controller.librarycomponent;

import java.util.List;

import com.globallogic.amcr.persistence.model.librarycomponent.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.amcr.persistence.payload.librarycomponent.BookResponse;
import com.globallogic.amcr.service.librarycomponent.BookServiceImpl;

@RestController
@RequestMapping("/library")
@CrossOrigin
public class BookController {
    private final BookServiceImpl bookServiceImpl;

    public BookController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @GetMapping(value ="/", produces="application/json")
    public List<BookResponse> getMany() {
        return bookServiceImpl.getAll();
    }

    @PostMapping("/upload")
    public ResponseEntity<?> saveBook(@RequestBody @Validated Book book, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new RuntimeException(errors.toString());
        }
        Book returnedBook = bookServiceImpl.save(book);
        return ResponseEntity.ok().body(returnedBook);
    }

}
