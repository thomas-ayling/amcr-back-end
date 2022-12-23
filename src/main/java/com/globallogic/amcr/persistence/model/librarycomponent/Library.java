package com.globallogic.amcr.persistence.model.librarycomponent;

import java.util.UUID;
import java.util.Objects;

public class Library {
    
    private UUID id;
    private String name;
    private String genre;
    private String author;
    private String reader;
    private Boolean out;
    private String cover;


    public Library() {
    }

    public Library(UUID id, String name, String genre, String author, String reader, Boolean out, String cover) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.reader = reader;
        this.out = out;
        this.cover = cover;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean isOut() {
        return this.out;
    }

    public Boolean getOut() {
        return this.out;
    }

    public void setOut(Boolean out) {
        this.out = out;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Library id(UUID id) {
        setId(id);
        return this;
    }

    public Library name(String name) {
        setName(name);
        return this;
    }

    public Library genre(String genre) {
        setGenre(genre);
        return this;
    }

    public Library author(String author) {
        setAuthor(author);
        return this;
    }

    public Library reader(String reader) {
        setReader(reader);
        return this;
    }

    public Library out(Boolean out) {
        setOut(out);
        return this;
    }

    public Library cover(String cover) {
        setCover(cover);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Library)) {
            return false;
        }
        Library library = (Library) o;
        return Objects.equals(id, library.id) && Objects.equals(name, library.name) && Objects.equals(genre, library.genre) && Objects.equals(author, library.author) && Objects.equals(reader, library.reader) && Objects.equals(out, library.out) && Objects.equals(cover, library.cover);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genre, author, reader, out, cover);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", genre='" + getGenre() + "'" +
            ", author='" + getAuthor() + "'" +
            ", reader='" + getReader() + "'" +
            ", out='" + isOut() + "'" +
            ", cover='" + getCover() + "'" +
            "}";
    }

}
