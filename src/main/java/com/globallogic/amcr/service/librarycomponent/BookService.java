package com.globallogic.amcr.service.librarycomponent;

import java.util.List;
import java.util.UUID;

import com.globallogic.amcr.persistence.model.librarycomponent.Book;

public interface BookService {

    /**
     * Method saves a new book to the database.
     * @param book - Book Object containing all the new information about the book.
     * @return returns the object that has been created.
     */
    Book save(Book book);

    /**
     * Method that gets a specific book from the database.
     * @param id - Book id that identifies the book to retrieve.
     * @return The book specified with the id and all its content.
     */
    Book get(UUID id);

    /**
     * Method that gets all the books from the database.
     * @return A list of books with all there data inside book objects.
     */
    List<Book> getAll();

    /**
     * Method for reserving a book from the database for a reader.
     * @param id - Specific book id for identifying book to reserve.
     * @param reservedBook - Object including information about the reader for the reserved book.
     * @return book object containing all the books' information.
     */
    Book reserve(UUID id, Book reservedBook);

    /**
     * Method for deleting book from the database.
     * @param id - book id for specifying which book to remove from the database.
     */
    void delete(UUID id);
    
}
