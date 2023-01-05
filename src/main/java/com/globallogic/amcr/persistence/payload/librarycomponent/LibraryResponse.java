package com.globallogic.amcr.persistence.payload.librarycomponent;

public class LibraryResponse {

    private String title;
    private String genre;
    private String author;
    private String reader;
    private Boolean available;
    private String cover;


    public LibraryResponse(String title, String genre, String author, String reader, Boolean available, String cover) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.reader = reader;
        this.available = available;
        this.cover = cover;
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

    
}
