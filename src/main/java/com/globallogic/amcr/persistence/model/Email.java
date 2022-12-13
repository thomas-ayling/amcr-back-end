package com.globallogic.amcr.persistence.model;

import java.util.Objects;

public class Email {
    private String sender;
    private String recipient;
    private String subject;
    private String messageBody;

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
