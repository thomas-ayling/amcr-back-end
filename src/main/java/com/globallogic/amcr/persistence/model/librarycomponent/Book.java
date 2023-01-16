package com.globallogic.amcr.persistence.model.librarycomponent;

import com.globallogic.amcr.utils.Assert;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

public class Book {

    private UUID id;
    @NotNull
    private String title;
    @NotNull
    private String genre;
    @NotNull
    private String author;
    private String reader;
    @NotNull
    private Boolean available;
    @NotNull
    private String cover;
    private String email;

    public Book() {
    }

    public Book(UUID id, String title, String genre, String author, String reader, Boolean available, String cover, String email) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.reader = reader;
        this.available = available;
        this.cover = cover;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = Assert.assertNotNull(id, "id cannot be null");;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = Assert.assertNotNull(title, "title cannot be null");;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = Assert.assertNotNull(genre, "genre cannot be null");;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = Assert.assertNotNull(author, "author cannot be null");;
    }

    public String getReader() {
        return this.reader;
    }

    public void setReader(String reader) {
        this.reader = Assert.assertNotNull(reader, "Reader cannot be null");;
    }

    public Boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(Boolean available) {
        this.available = Assert.assertNotNull(available, "Available cannot be null");;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCover(String cover) {
        this.cover = Assert.assertNotNull(cover, "Cover cannot be null");;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = Assert.assertNotNull(email, "Email cannot be null");;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Book book)) {
            return false;
        }
        return Objects.equals(title, book.title) && Objects.equals(genre, book.genre) && Objects.equals(author, book.author) && Objects.equals(reader, book.reader) && Objects.equals(available, book.available) && Objects.equals(cover, book.cover) && Objects.equals(email, book.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public String toString() {
        return "{" +
            " title='" + getTitle() + "'" +
            ", genre='" + getGenre() + "'" +
            ", author='" + getAuthor() + "'" +
            ", reader='" + getReader() + "'" +
            ", available='" + isAvailable() + "'" +
            ", cover='" + getCover() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }

}