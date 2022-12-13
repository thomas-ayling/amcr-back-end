package com.globallogic.amcr.service.contactcomponent;

import com.globallogic.amcr.persistence.dao.contactcomponent.FileDao;
import com.globallogic.amcr.persistence.model.contactcomponent.Email;
import com.globallogic.amcr.persistence.model.contactcomponent.Feedback;
import com.globallogic.amcr.persistence.payload.contactcomponent.AttachmentMetadata;
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
public class EmailService {
    private final JavaMailSender mailSender;
    private final FileDao fileDao;

    public EmailService(JavaMailSender mailSender, FileDao fileDao) {
        this.mailSender = mailSender;
        this.fileDao = fileDao;
    }

    @Transactional
    public ResponseEntity sendMail(Feedback feedback, UUID feedbackId, int tries) {
        try {
            AttachmentMetadata attachmentMetadata = fileDao.getAttachmentMetadata(feedbackId);

            Email email = EmailGenerator.generateEmailFromFeedback(feedback, attachmentMetadata);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            mimeMessageHelper.setTo(email.getRecipient());
            mimeMessageHelper.setFrom(email.getSender());
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setText(email.getMessageBody(), true);

            mailSender.send(mimeMessage);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            if (tries < 3) {
                tries ++;
                return sendMail(feedback, feedbackId, tries);
            }
            throw new MailSendException("There was a problem sending this email", e);
        }
    }
}
