package com.globallogic.amcr.model.sharedcomponents;

import java.util.Objects;
import java.util.UUID;
public class MainCarousel {

    private UUID id;
    private String title;
    private String location;
    private UUID imageID;

    public MainCarousel(UUID id, String title, String location, UUID imageID) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.imageID = imageID;
    }

    public MainCarousel(){}

    @Override
    public String toString() {
        return "MainCarousel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", imageID=" + imageID +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UUID getImageID() {
        return imageID;
    }

    public void setImageID(UUID imageID) {
        this.imageID = imageID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MainCarousel that)) return false;
        return id.equals(that.id) && title.equals(that.title) && location.equals(that.location) && imageID.equals(that.imageID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, location, imageID);
    }
}

