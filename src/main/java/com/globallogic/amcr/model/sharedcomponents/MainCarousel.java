package com.globallogic.amcr.model.sharedcomponents;

import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
public class MainCarousel {

    private UUID id;
    private String[] titles;
    private String location;
    private String[] descriptions;
    private UUID[] imageIds;
    private transient List<String> imageLinks;

    public MainCarousel(UUID id, String[] titles, String location, String[] descriptions, UUID[] imageIds) {
        this.id = id;
        this.titles = titles;
        this.location = location;
        this.descriptions = descriptions;
        this.imageIds = imageIds;
    }

    public MainCarousel(){}

    @Override
    public String toString() {
        return "MainCarousel{" +
                "id=" + id +
                ", titles=" + titles +
                ", location='" + location + '\'' +
                ", descriptions=" + descriptions +
                ", imageIds=" + Arrays.toString(imageIds) +
                ", imageLinks=" + imageLinks +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String[] getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String[] descriptions) {
        this.descriptions = descriptions;
    }

    public UUID[] getImageIds() {
        return imageIds;
    }

    public void setImageIds(UUID[] imageIds) {
        this.imageIds = imageIds;
    }

    public List<String> getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(List<String> imageLinks) {
        this.imageLinks = imageLinks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MainCarousel that)) return false;
        return Objects.equals(id, that.id) && Arrays.equals(titles, that.titles) && Objects.equals(location, that.location) && Arrays.equals(descriptions, that.descriptions) && Arrays.equals(imageIds, that.imageIds);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, location);
        result = 31 * result + Arrays.hashCode(titles);
        result = 31 * result + Arrays.hashCode(descriptions);
        result = 31 * result + Arrays.hashCode(imageIds);
        return result;
    }
}

