package com.globallogic.amcr.service;

import com.globallogic.amcr.model.Email;
import com.globallogic.amcr.model.Feedback;
import com.globallogic.amcr.payload.AttachmentMetadata;
import com.globallogic.amcr.utils.EmailGenerator;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

@Service
public class EmailService {
    private JavaMailSender mailSender;
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public ResponseEntity sendMail(Feedback feedback, AttachmentMetadata attachmentMetadata, int tries) {
        try {
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
                System.out.println(tries);
                System.out.println(e.getMessage());
                return sendMail(feedback, attachmentMetadata, tries);
            }
            throw new MailSendException("There was a problem sending this email", e);
        }
    }


}
