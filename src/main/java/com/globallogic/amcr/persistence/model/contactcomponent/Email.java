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
    private String textPart;
    private String htmlPart;

    /**
     * @param sender    the email address of the user who left feedback - cannot be null so if the feedback is anonymous a spoof address is generated in its place
     * @param recipient the recipient of the feedback - always <engineeringcenterbot@globallogic.com>
     * @param subject   the subject of the email - changes depending on the feedback type
     * @param htmlPart  the main content of the email, the user's name, the feedback body and an attachment link are included in the messageBody
     */
    public Email(String sender, String recipient, String subject, String textPart, String htmlPart) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.textPart = textPart;
        this.htmlPart = htmlPart;
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

    public String getTextPart() {
        return textPart;
    }

    public void setTextPart(String textPart) {
        this.textPart = textPart;
    }

    public String getHtmlPart() {
        return htmlPart;
    }

    public void setHtmlPart(String htmlPart) {
        this.htmlPart = htmlPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return getSender().equals(email.getSender()) && getRecipient().equals(email.getRecipient()) && getSubject().equals(email.getSubject()) && getTextPart().equals(email.getTextPart()) && getHtmlPart().equals(email.getHtmlPart());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSender(), getRecipient(), getSubject(), getTextPart(), getHtmlPart());
    }

    @Override
    public String toString() {
        return "Email{" + "sender='" + sender + '\'' + ", recipient='" + recipient + '\'' + ", subject='" + subject + '\'' + ", textPart='" + textPart + '\'' + ", htmlPart='" + htmlPart + '\'' + '}';
    }
}
