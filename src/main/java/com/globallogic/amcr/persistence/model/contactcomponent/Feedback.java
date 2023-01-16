package com.globallogic.amcr.persistence.model.contactcomponent;

import com.globallogic.amcr.utils.Assert;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

/**
 * Feedback model for submitted feedback
 */
public class Feedback {
    private UUID id;
    @NotNull
    private String feedbackType;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String feedbackBody;
    private String bookName;
    private String bookLink;

    /**
     * @param feedbackType the type of the feedback submitted, variable will be used for formatting once data is received by admin panel
     * @param firstName    first name of the user that left the feedback, null if feedback was anonymous
     * @param lastName     last name of the user that left the feedback, null if feedback was anonymous
     * @param emailAddress email address of the user that left the feedback, null if feedback was anonymous
     * @param feedbackBody the main body of text that was left in the feedback form, not null
     * @param bookName     the name of a book requested through the library form, always null if feedback type is not 'library'
     * @param bookLink     link to the book that the user has requested, optional and always null if feedback type is not 'library'
     */

    public Feedback(String feedbackType, String firstName, String lastName, String emailAddress, String feedbackBody, String bookName, String bookLink) {
        if (!feedbackType.equals("library")) Assert.assertNotNull(feedbackBody, "feedbackBody is null");
        if (feedbackType.equals("library")) Assert.assertNotNull(bookLink, "bookLink is null");

        this.feedbackType = Assert.assertNotNull(feedbackType, "feedbackType is null");
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.feedbackBody = feedbackBody;
        this.bookName = bookName;
        this.bookLink = bookLink;
    }

    public Feedback() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = Assert.assertNotNull(id, "id is null");

    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = Assert.assertNotNull(feedbackType, "feedbackType is null");

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = Assert.assertNotNull(firstName, "firstName is null");

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = Assert.assertNotNull(lastName, "lastName is null");

    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = Assert.assertNotNull(emailAddress, "emailAddress is null");

    }

    public String getFeedbackBody() {
        return feedbackBody;
    }

    public void setFeedbackBody(String feedbackBody) {
        this.feedbackBody = Assert.assertNotNull(feedbackBody, "feedbackBody is null");
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = Assert.assertNotNull(bookName, "bookName is null");

    }

    public String getBookLink() {
        return bookLink;
    }

    public void setBookLink(String bookLink) {
        this.bookLink = Assert.assertNotNull(bookLink, "bookLink is null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return getId().equals(feedback.getId()) && getFeedbackType().equals(feedback.getFeedbackType()) && getFirstName().equals(feedback.getFirstName()) && getLastName().equals(feedback.getLastName()) && getEmailAddress().equals(feedback.getEmailAddress()) && getFeedbackBody().equals(feedback.getFeedbackBody()) && getBookName().equals(feedback.getBookName()) && getBookLink().equals(feedback.getBookLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFeedbackType(), getFirstName(), getLastName(), getEmailAddress(), getFeedbackBody(), getBookName(), getBookLink());
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", feedbackType='" + feedbackType + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", emailAddress='" + emailAddress + '\'' + ", feedbackBody='" + feedbackBody + '\'' + ", bookName='" + bookName + '\'' + ", bookLink='" + bookLink + '\'' + '}';
    }
}
