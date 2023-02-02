package com.globallogic.amcr.model.contacts;

import com.globallogic.amcr.utils.Assert;
import jakarta.validation.constraints.NotNull;

import java.beans.Transient;
import java.util.Objects;
import java.util.UUID;

/**
 * Contacts model for the Contacts page carousel
 */
public class Contacts {

    private UUID id;

    @NotNull
    private boolean spotlight;

    @NotNull
    private UUID imageId;

    private transient String imageLink;

    @NotNull
    private String fullName;

    @NotNull
    private String title;

    @NotNull
    private String description;

    /**
     * @param spotlight   whether the employee is spotlit and will be shown on the carousel
     * @param imageId     the ID for an employee's image
     * @param fullName    the name of the employee
     * @param title       the job title of the employee
     * @param description a description that presents more information about the employee
     */
    public Contacts(UUID id, boolean spotlight, UUID imageId, String fullName, String title, String description) {
        setId(id);
        setSpotlight(spotlight);
        setImageId(imageId);
        setFullName(fullName);
        setTitle(title);
        setDescription(description);
    }

    public Contacts() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = Assert.assertNotNull(id, "ID cannot be null");
    }

    public boolean isSpotlight() {
        return spotlight;
    }

    public void setSpotlight(boolean spotlight) {
        this.spotlight = Assert.assertNotNull(spotlight, "Spotlight cannot be null");
    }

    public UUID getImageId() {
        return imageId;
    }

    public void setImageId(UUID imageId) {
        this.imageId = Assert.assertNotNull(imageId, "Image ID cannot be null");
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = Assert.assertNotNull(fullName, "Name cannot be null");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = Assert.assertNotNull(title, "Job title cannot be null");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = Assert.assertNotNull(description, "Description cannot be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacts contacts = (Contacts) o;
        return spotlight == contacts.spotlight && Objects.equals(imageLink, contacts.imageLink) && fullName.equals(contacts.fullName) && title.equals(contacts.title) && description.equals(contacts.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spotlight, imageLink, fullName, title, description);
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "id=" + id +
                ", spotlight=" + spotlight +
                ", imageId=" + imageId +
                ", imageLink='" + imageLink + '\'' +
                ", fullName='" + fullName + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}