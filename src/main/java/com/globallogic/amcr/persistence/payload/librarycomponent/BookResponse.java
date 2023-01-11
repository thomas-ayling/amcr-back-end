package com.globallogic.amcr.persistence.payload.librarycomponent;

import java.util.Objects;

public class BookResponse {
    private String title;
    private String genre;
    private String author;
    private String reader;
    private Boolean available;
    private String cover;
    private String email;

    public BookResponse() {
    }

    public BookResponse(String title, String genre, String author, String reader, Boolean available, String cover, String email) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.reader = reader;
        this.available = available;
        this.cover = cover;
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BookResponse libraryResponse)) {
            return false;
        }
        return Objects.equals(title, libraryResponse.title) && Objects.equals(genre, libraryResponse.genre) && Objects.equals(author, libraryResponse.author) && Objects.equals(reader, libraryResponse.reader) && Objects.equals(available, libraryResponse.available) && Objects.equals(cover, libraryResponse.cover) && Objects.equals(email, libraryResponse.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, author, reader, available, cover, email);
    }

    @Override
    public String toString() {
        return "{" +
            ", title='" + getTitle() + "'" +
            ", genre='" + getGenre() + "'" +
            ", author='" + getAuthor() + "'" +
            ", reader='" + getReader() + "'" +
            ", available='" + isAvailable() + "'" +
            ", cover='" + getCover() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }

}
