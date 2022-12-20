package com.globallogic.amcr.utils;

import com.globallogic.amcr.contactcomponent.persistence.model.Email;
import com.globallogic.amcr.contactcomponent.persistence.model.Feedback;
import com.globallogic.amcr.contactcomponent.persistence.payload.AttachmentMetadata;

public class EmailGenerator {
    public final static Email generateEmailFromFeedback(Feedback feedback, AttachmentMetadata attachmentMetadata) {
        Email email = new Email();
        boolean isAnonymous = feedback.getEmailAddress() == null;
        email.setSender(isAnonymous ? "<anonymous@globallogic.com>" : "<" + feedback.getEmailAddress() + ">");
        email.setRecipient("<engineeringcenterbot@globallogic.com>");
        String style = "<style>.email { font-family: Trebuchet MS,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Tahoma,sans-serif; }</style>";
        String textAttachmentLink = attachmentMetadata == null ? "" : String.format("Follow this link to download attachment: %s (%s, %s)", attachmentMetadata.getDownloadUri(), attachmentMetadata.getFileName(), ByteConverter.bytesToReadable(attachmentMetadata.getFileSize()));
        String htmlAttachmentLink = attachmentMetadata == null ? "" : String.format("<span>Download attachment: <a href=%s>%s (%s)</a></span>", attachmentMetadata.getDownloadUri(), attachmentMetadata.getFileName(), ByteConverter.bytesToReadable(attachmentMetadata.getFileSize()));

        switch (feedback.getFeedbackType()) {
            case "case-study":
                email.setSubject("New case study proposal");
                email.setTextPart(String.format("%s %s has proposed a new case study:\n%s\n" + textAttachmentLink + "\nReturn address: %s", feedback.getFirstName(), feedback.getLastName(), feedback.getFeedbackBody(), feedback.getEmailAddress()));
                email.setHtmlPart(String.format(style + "<div class='email'><h2>%s %s has proposed a new case study:</h2><p>%s</p><br/>" + htmlAttachmentLink + "<br/><strong>Return address: %s</strong></div>", feedback.getFirstName(), feedback.getLastName(), feedback.getFeedbackBody(), feedback.getEmailAddress()));
                return email;

            case "feedback":
                email.setSubject("New feedback");
                email.setTextPart(isAnonymous ? String.format("Anonymous feedback has been submitted:\n", feedback.getFeedbackBody()) : String.format("%s %s has left some feedback:\n%s\nReturn address: %s", feedback.getFirstName(), feedback.getLastName(), feedback.getFeedbackBody(), feedback.getEmailAddress()));
                email.setHtmlPart(isAnonymous ? String.format(style + "<div class='email'><h2>Anonymous feedback has been submitted:</h2><p>%s</p></div>", feedback.getFeedbackBody()) : String.format(style + "<div class='email'><h2>%s %s has left some feedback:</h2><p>%s</p><br/><strong>Return address: %s</strong></div>", feedback.getFirstName(), feedback.getLastName(), feedback.getFeedbackBody(), feedback.getEmailAddress()));
                return email;

            case "library":
                email.setSubject("New book request");
                email.setTextPart(String.format("%s %s has requested a new book for the library:\n%s\nLink to book: %s\nReturn address: %s", feedback.getFirstName(), feedback.getLastName(), feedback.getBookName(), feedback.getBookLink(), feedback.getEmailAddress()));
                email.setHtmlPart(String.format(style + "<div class='email'><h2>%s %s has requested a new book for the library:</h2><p>%s</p><a href='%s' target='_blank' rel='noopener noreferrer'>Link to book</a><br/><strong>Return address: %s</strong></div>", feedback.getFirstName(), feedback.getLastName(), feedback.getBookName(), feedback.getBookLink(), feedback.getEmailAddress()));
                return email;

            case "improvement":
                email.setSubject("New improvement proposal");
                email.setTextPart(isAnonymous ? String.format("An anonymous improvement has been proposed:\n%s" + textAttachmentLink, feedback.getFeedbackBody()) : String.format("%s %s has proposed an improvement:\n" + textAttachmentLink + "\nReturn address: %s", feedback.getFirstName(), feedback.getLastName(), feedback.getFeedbackBody(), feedback.getEmailAddress()));
                email.setHtmlPart(isAnonymous ? String.format(style + "<div class='email'><h2>An anonymous improvement has been proposed:</h2><p>%s</p><br/>" + htmlAttachmentLink + "</div>", feedback.getFeedbackBody()) : String.format(style + "<div class='email'><h2>%s %s has proposed an improvement:</h2><p>%s</p><br/>" + htmlAttachmentLink + "<br/><strong>Return address: %s</strong></div>", feedback.getFirstName(), feedback.getLastName(), feedback.getFeedbackBody(), feedback.getEmailAddress()));
                return email;
            default:
                return email;
        }
    }
}
