package com.globallogic.amcr.persistence.model.librarycomponent;

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
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReader() {
        return this.reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public Boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Book title(String title) {
        setTitle(title);
        return this;
    }

    public Book genre(String genre) {
        setGenre(genre);
        return this;
    }

    public Book author(String author) {
        setAuthor(author);
        return this;
    }

    public Book reader(String reader) {
        setReader(reader);
        return this;
    }

    public Book available(Boolean available) {
        setAvailable(available);
        return this;
    }

    public Book cover(String cover) {
        setCover(cover);
        return this;
    }

    public Book email(String email) {
        setEmail(email);
        return this;
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
        return Objects.hash(title, genre, author, reader, available, cover, email);
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