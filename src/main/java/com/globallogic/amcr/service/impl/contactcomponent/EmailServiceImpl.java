package com.globallogic.amcr.service.impl.contactcomponent;

import com.globallogic.amcr.repository.contactcomponent.FeedbackAttachmentDao;
import com.globallogic.amcr.model.contactcomponent.Feedback;
import com.globallogic.amcr.model.contactcomponent.FeedbackAttachmentMetadata;
import com.globallogic.amcr.service.contactcomponent.EmailService;
import com.globallogic.amcr.utils.Assert;
import com.globallogic.amcr.utils.FormatUtil;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
@Service
public class EmailServiceImpl implements EmailService {
    private final Logger Log = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final JavaMailSender mailSender;
    private final FeedbackAttachmentDao feedbackAttachmentDao;

    public EmailServiceImpl(JavaMailSender mailSender, FeedbackAttachmentDao feedbackAttachmentDao) {
        this.mailSender = Assert.assertNotNull(mailSender, "Mail sender cannot be null");
        this.feedbackAttachmentDao = Assert.assertNotNull(feedbackAttachmentDao, "File DAO cannot be null");
    }

    @Override
    public void sendMail(Feedback feedback, UUID feedbackId) {
        try {
            // Get data for attachment link
            Log.debug("Requesting download link data for email");
            FeedbackAttachmentMetadata feedbackAttachmentMetadata = feedbackAttachmentDao.getAttachmentMetadata(feedbackId);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            boolean isAnonymous = feedback.getEmailAddress() == null || feedback.getEmailAddress().length() == 0;

            Log.debug("Generating email. isAnonymous is {}", isAnonymous);

            mimeMessageHelper.setFrom(isAnonymous ? "<anonymous@globallogic.com>" : "<" + feedback.getEmailAddress() + ">");
            mimeMessageHelper.setTo("<test>");

            String style = "<style>.email { font-family: Trebuchet MS,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Tahoma,sans-serif; }</style>";
            String textAttachmentLink = feedbackAttachmentMetadata == null ? "" : String.format("Follow this link to download attachment: %s (%s, %s)", feedbackAttachmentMetadata.getDownloadUri(), feedbackAttachmentMetadata.getAttachmentName(), FormatUtil.bytesToReadable(feedbackAttachmentMetadata.getAttachmentSize()));
            String htmlAttachmentLink = feedbackAttachmentMetadata == null ? "" : String.format("<span>Download attachment: <a href=%s>%s (%s)</a></span>", feedbackAttachmentMetadata.getDownloadUri(), feedbackAttachmentMetadata.getAttachmentName(), FormatUtil.bytesToReadable(feedbackAttachmentMetadata.getAttachmentSize()));
            String textPart;
            String htmlPart;

            switch (feedback.getFeedbackType()) {
                case "case-study" -> {
                    mimeMessageHelper.setSubject("New case study proposal");
                    textPart = String.format("%s %s has proposed a new case study:\n%s\n%s\nReturn address: %s", feedback.getFirstName(), feedback.getLastName(), feedback.getFeedbackBody(), textAttachmentLink, feedback.getEmailAddress());
                    htmlPart = String.format("%s<div class='email'><h2>%s %s has proposed a new case study:</h2><p>%s</p><br/>%s<br/><strong>Return address: %s</strong></div>", style, feedback.getFirstName(), feedback.getLastName(), feedback.getFeedbackBody(), htmlAttachmentLink, feedback.getEmailAddress());
                }
                case "feedback" -> {
                    mimeMessageHelper.setSubject("New feedback");
                    textPart = isAnonymous ? String.format("Anonymous feedback has been submitted:\n%s", feedback.getFeedbackBody()) : String.format("%s %s has left some feedback:\n%s\nReturn address: %s", feedback.getFirstName(), feedback.getLastName(), feedback.getFeedbackBody(), feedback.getEmailAddress());
                    htmlPart = isAnonymous ? String.format("%s<div class='email'><h2>Anonymous feedback has been submitted:</h2><p>%s</p></div>", style, feedback.getFeedbackBody()) : String.format("%s<div class='email'><h2>%s %s has left some feedback:</h2><p>%s</p><br/><strong>Return address: %s</strong></div>", style, feedback.getFirstName(), feedback.getLastName(), feedback.getFeedbackBody(), feedback.getEmailAddress());
                }
                case "library" -> {
                    mimeMessageHelper.setSubject("New book request");
                    textPart = String.format("%s %s has requested a new book for the library:\n%s\nLink to book: %s\nReturn address: %s", feedback.getFirstName(), feedback.getLastName(), feedback.getBookName(), feedback.getBookLink(), feedback.getEmailAddress());
                    htmlPart = String.format("%s<div class='email'><h2>%s %s has requested a new book for the library:</h2><p>%s</p><a href='%s' target='_blank' rel='noopener noreferrer'>Link to book</a><br/><strong>Return address: %s</strong></div>", style, feedback.getFirstName(), feedback.getLastName(), feedback.getBookName(), feedback.getBookLink(), feedback.getEmailAddress());
                }
                case "improvement" -> {
                    mimeMessageHelper.setSubject("New improvement proposal");
                    textPart = isAnonymous ? String.format("An anonymous improvement has been proposed:\n%s\n%s", feedback.getFeedbackBody(), textAttachmentLink) : String.format("%s %s has proposed an improvement:\n%s\n%s\nReturn address: %s", feedback.getFirstName(), feedback.getLastName(), feedback.getFeedbackBody(), textAttachmentLink, feedback.getEmailAddress());
                    htmlPart = isAnonymous ? String.format("%s<div class='email'><h2>An anonymous improvement has been proposed:</h2><p>%s</p><br/>%s</div>", style, feedback.getFeedbackBody(), htmlAttachmentLink) : String.format(style + "<div class='email'><h2>%s %s has proposed an improvement:</h2><p>%s</p><br/>%s<br/><strong>Return address: %s</strong></div>", style, feedback.getFirstName(), feedback.getLastName(), feedback.getFeedbackBody(), htmlAttachmentLink, feedback.getEmailAddress());
                }
                default -> {
                    textPart = "There was an issue generating this email";
                    htmlPart = "There was an issue generating this email";
                }
            }
            mimeMessageHelper.setText(textPart, htmlPart);
            Log.debug("Sending email.\nText part is:\n{}\n\nHTML part is:\n{}\n\nisAnonumous value is: {}", textPart, htmlPart, isAnonymous);
            mailSender.send(mimeMessage);
            Log.debug("Email sent");
        } catch (Exception e) {
            throw new MailSendException("There was a problem sending this email", e);
        }
    }
}