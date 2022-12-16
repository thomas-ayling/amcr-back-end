package com.globallogic.amcr.persistence.model.contactcomponent;

import java.util.Objects;

/**
 * Email model for an email that is to be sent
 * Emails are generated in the EmailGenerator found in com.globallogic.amcr.utils
 */
public class Email {
    private String sender;
    private String recipient;
    private String subject;
    private String messageBody;

    /**
     *
     * @param sender the email address of the user who left feedback - cannot be null so if the feedback is anonymous a spoof address is generated in its place
     * @param recipient the recipient of the feedback - always <engineeringcenterbot@globallogic.com>
     * @param subject the subject of the email - changes depending on the feedback type
     * @param messageBody the main content of the email, the user's name, the feedback body and an attachment link are included in the messageBody
     */
    public Email(String sender, String recipient, String subject, String messageBody) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.messageBody = messageBody;
    }

    public Email() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return getSender().equals(email.getSender()) && getRecipient().equals(email.getRecipient()) && getSubject().equals(email.getSubject()) && getMessageBody().equals(email.getMessageBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSender(), getRecipient(), getSubject(), getMessageBody());
    }
}
