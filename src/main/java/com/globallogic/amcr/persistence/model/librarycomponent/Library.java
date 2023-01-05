package com.globallogic.amcr.persistence.model.librarycomponent;

import java.util.UUID;
import java.util.Objects;

public class Library {
    
    private UUID id;
    private String title;
    private String genre;
    private String author;
    private String reader;
    private Boolean available;
    private String cover;


    public Library() {
    }


    public Library(UUID id, String title, String genre, String author, String reader, Boolean available, String cover) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.reader = reader;
        this.available = available;
        this.cover = cover;
    }


    public UUID getId() {
        return this.id;
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

    public Boolean getAvailable() {
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


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", genre='" + getGenre() + "'" +
            ", author='" + getAuthor() + "'" +
            ", reader='" + getReader() + "'" +
            ", available='" + isAvailable() + "'" +
            ", cover='" + getCover() + "'" +
            "}";
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Library)) {
            return false;
        }
        Library library = (Library) o;
        return Objects.equals(id, library.id) && Objects.equals(title, library.title) && Objects.equals(genre, library.genre) && Objects.equals(author, library.author) && Objects.equals(reader, library.reader) && Objects.equals(available, library.available) && Objects.equals(cover, library.cover);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, author, reader, available, cover);
    }

    

}
