package com.globallogic.amcr.contactcomponent.service;

import com.globallogic.amcr.contactcomponent.persistence.dao.FileDao;
import com.globallogic.amcr.contactcomponent.persistence.model.Email;
import com.globallogic.amcr.contactcomponent.persistence.model.Feedback;
import com.globallogic.amcr.contactcomponent.persistence.payload.AttachmentMetadata;
import com.globallogic.amcr.utils.EmailGenerator;
import jakarta.mail.internet.MimeMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final FileDao fileDao;

    public EmailServiceImpl(JavaMailSender mailSender, FileDao fileDao) {
        this.mailSender = mailSender;
        this.fileDao = fileDao;
    }

    @Transactional
    public ResponseEntity sendMail(Feedback feedback, UUID feedbackId) {
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

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new MailSendException("There was a problem sending this email", e);
        }
    }
}
