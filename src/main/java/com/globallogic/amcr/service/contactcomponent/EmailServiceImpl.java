package com.globallogic.amcr.service.contactcomponent;

import com.globallogic.amcr.persistence.dao.contactcomponent.FileDao;
import com.globallogic.amcr.persistence.model.contactcomponent.Email;
import com.globallogic.amcr.persistence.model.contactcomponent.Feedback;
import com.globallogic.amcr.persistence.payload.contactcomponent.AttachmentMetadata;
import com.globallogic.amcr.utils.EmailGenerator;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final FileDao fileDao;

    public EmailServiceImpl(JavaMailSender mailSender, FileDao fileDao) {
        this.mailSender = mailSender;
        this.fileDao = fileDao;
    }

    public boolean sendMail(Feedback feedback, UUID feedbackId) {
        try {
            AttachmentMetadata attachmentMetadata = fileDao.getAttachmentMetadata(feedbackId);

            Email email = EmailGenerator.generateEmailFromFeedback(feedback, attachmentMetadata);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo(email.getRecipient());
            mimeMessageHelper.setFrom(email.getSender());
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setText(email.getTextPart(), email.getHtmlPart());
            mailSender.send(mimeMessage);

            return true;
        } catch (Exception e) {
            throw new MailSendException("There was a problem sending this email", e);
        }
    }
}
