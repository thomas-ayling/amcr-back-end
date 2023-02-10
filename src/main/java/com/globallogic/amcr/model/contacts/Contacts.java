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

    private String email;

    @NotNull
    private int orderNum;

    /**
     * @param spotlight   whether the employee is spotlit and will be shown on the carousel
     * @param imageId     the ID for an employee's image
     * @param fullName    the name of the employee
     * @param title       the job title of the employee
     * @param description a description that presents more information about the employee
     * @param email the email address of each employee
     * @param orderNum the order number the employee has in the carousel
     */
    public Contacts(UUID id, boolean spotlight, UUID imageId, String fullName, String title, String description, String email, int orderNum) {
        setId(id);
        setSpotlight(spotlight);
        setImageId(imageId);
        setFullName(fullName);
        setTitle(title);
        setDescription(description);
        setEmail(email);
        setOrderNum(orderNum);
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

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public int getOrderNum() { return orderNum; }

    public void setOrderNum(int orderNum) {
        this.orderNum = Assert.assertNotNull(orderNum, "Order number cannot be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacts contacts = (Contacts) o;
        return spotlight == contacts.spotlight && orderNum == contacts.orderNum && imageLink.equals(contacts.imageLink) && fullName.equals(contacts.fullName) && title.equals(contacts.title) && description.equals(contacts.description) && Objects.equals(email, contacts.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spotlight, imageLink, fullName, title, description, email, orderNum);
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
                ", email='" + email + '\'' +
                ", orderNum=" + orderNum +
                '}';
    }
}