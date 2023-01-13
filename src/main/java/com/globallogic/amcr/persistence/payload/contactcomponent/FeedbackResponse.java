package com.globallogic.amcr.persistence.payload.contactcomponent;

import com.globallogic.amcr.utils.Assert;
import jakarta.validation.constraints.NotNull;

/**
 * Response object for feedback requests from the database
 */
@SuppressWarnings("unused")
public class FeedbackResponse {
    @NotNull
    private Integer feedbackOrder;
    @NotNull
    private String feedbackType;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String feedbackBody;
    private String bookName;
    private String bookLink;
    private String downloadUri;

    /**
     * @param feedbackOrder the order in which the feedback was submitted for pagination purposes
     * @param feedbackType  the type of the feedback submitted, variable will be used for formatting once data is received by admin panel
     * @param firstName     the first name of the user that left the feedback, null if feedback was anonymous
     * @param lastName      the last name of the user that left the feedback, null if feedback was anonymous
     * @param emailAddress  the email address of the user that left the feedback, null if feedback was anonymous
     * @param feedbackBody  the main chunk of text that was left in the feedback form, not null
     * @param bookName      the name of a book requested through the library form, always null if feedback type is not 'library'
     * @param bookLink      the link to the book that the user has requested, optional and always null if feedback type  is not 'library'
     * @param downloadUri   the download link to any related attachments retrieved from the attachments table using the foreign key, null if no attachments were uploaded with the feedback
     */

    public FeedbackResponse(Integer feedbackOrder, String feedbackType, String firstName, String lastName, String emailAddress, String feedbackBody, String bookName, String bookLink, String downloadUri) {
        this.feedbackOrder = Assert.assertNull(feedbackOrder, "Feedback order cannot be null");
        this.feedbackType = Assert.assertNull(feedbackType, "Feedback type cannot be null");
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.feedbackBody = feedbackBody;
        this.bookName = bookName;
        this.bookLink = bookLink;
        this.downloadUri = downloadUri;
    }

    public Integer getFeedbackOrder() {
        return feedbackOrder;
    }

    public void setFeedbackOrder(Integer feedbackOrder) {
        this.feedbackOrder = Assert.assertNull(feedbackOrder, "Feedback order cannot be null");
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = Assert.assertNull(feedbackType, "Feedback type cannot be null");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFeedbackBody() {
        return feedbackBody;
    }

    public void setFeedbackBody(String feedbackBody) {
        this.feedbackBody = feedbackBody;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookLink() {
        return bookLink;
    }

    public void setBookLink(String bookLink) {
        this.bookLink = bookLink;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }
}
