package com.globallogic.amcr.model.sharedcomponents;

import com.globallogic.amcr.utils.Assert;

import java.util.UUID;
public class BehavourCarousel {
    private UUID id;
    private String title;
    private String[] description;
    private String image;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = Assert.assertNotNull(id, "ID cannot be null");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = Assert.assertNotNull(title, "title cannot be null");
    }

    public String[] getDescription() {
        return description;
    }

    public void setDescription(String[] description) {
        this.description = Assert.assertNotNull(description, "String Object cannot be null");
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = Assert.assertNotNull(image, "title cannot be null");
    }
}
