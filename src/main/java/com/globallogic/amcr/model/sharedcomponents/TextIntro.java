package com.globallogic.amcr.model.sharedcomponents;

import com.globallogic.amcr.utils.Assert;

import java.util.Objects;
import java.util.UUID;
public class TextIntro {
    private UUID id;
    private String title;
    private String description;
    private String location;

    public TextIntro(UUID id, String title, String description, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
    }

    public TextIntro(){}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextIntro textIntro)) return false;
        return Objects.equals(id, textIntro.id) && Objects.equals(title, textIntro.title) && Objects.equals(description, textIntro.description) && Objects.equals(location, textIntro.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, location);
    }
}
