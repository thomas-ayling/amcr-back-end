package com.globallogic.amcr.persistence.model.contactcomponent;

import org.springframework.util.Assert;

import java.util.Objects;
import java.util.UUID;

/**
 * Feedback model for submitted feedback
 */
public class Feedback {

    private UUID id;
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
        Objects.requireNonNull(feedbackType, "feedbackType is null");
        if (!feedbackType.equals("library")) Assert.notNull(feedbackBody, "feedbackBody is null");
        if (feedbackType.equals("library")) Assert.notNull(bookLink, "bookLink is null");

        this.feedbackType = feedbackType;
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
        Assert.notNull(id, "id is null");
        this.id = id;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        Objects.requireNonNull(feedbackType, "feedbackType is null");
        this.feedbackType = feedbackType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        Objects.requireNonNull(firstName, "firstName is null");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        Objects.requireNonNull(lastName, "lastName is null");
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        Objects.requireNonNull(emailAddress, "emailAddress is null");
        this.emailAddress = emailAddress;
    }

    public String getFeedbackBody() {
        return feedbackBody;
    }

    public void setFeedbackBody(String feedbackBody) {
        Objects.requireNonNull(feedbackBody, "feedbackBody is null");
        this.feedbackBody = feedbackBody;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        Objects.requireNonNull(bookName, "bookName is null");
        this.bookName = bookName;
    }

    public String getBookLink() {
        return bookLink;
    }

    public void setBookLink(String bookLink) {
        Objects.requireNonNull(bookLink, "bookLink is null");
        this.bookLink = bookLink;
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
        return "Feedback{" +
                "id=" + id +
                ", feedbackType='" + feedbackType + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", feedbackBody='" + feedbackBody + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookLink='" + bookLink + '\'' +
                '}';
    }
}
