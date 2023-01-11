package com.globallogic.amcr.service.contactcomponent;

import com.globallogic.amcr.persistence.dao.contactcomponent.FeedbackDao;
import com.globallogic.amcr.persistence.dao.contactcomponent.FileDao;
import com.globallogic.amcr.persistence.model.contactcomponent.Attachment;
import com.globallogic.amcr.persistence.model.contactcomponent.Feedback;
import com.globallogic.amcr.persistence.payload.contactcomponent.AttachmentResponse;
import com.globallogic.amcr.persistence.payload.contactcomponent.FeedbackResponse;
import com.globallogic.amcr.utils.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackDao feedbackDao;
    private final FileDao fileDao;
    private final EmailService emailService;

    public FeedbackServiceImpl(FeedbackDao feedbackDao, FileDao fileDao, EmailService emailService) {
        this.feedbackDao = Assert.assertNull(feedbackDao, "Feedback DAO cannot be null");
        this.fileDao = Assert.assertNull(fileDao, "File DAO cannot be null");
        this.emailService = Assert.assertNull(emailService, "Email service cannot be null");
    }

    @Transactional
    public Feedback save(Feedback feedback, Attachment attachment) {
        try {
            UUID feedbackId = UUID.randomUUID();
            // save feedback to db
            Feedback returnedFeedback = feedbackDao.save(feedback, feedbackId);
            // save attachment if exists
            if (attachment != null) {
                fileDao.save(attachment, feedbackId);
            }
            // send mail at end of method as we need the file data before sending mail
            emailService.sendMail(feedback, feedbackId);
            // return feedback if saved with no errors
            return returnedFeedback;
        } catch (Exception e) {
            throw new RuntimeException("Error saving feedback and attachment to database", e);
        }
    }

    @Transactional(readOnly = true)
    public List<FeedbackResponse> getAll() {
        return feedbackDao.getAll();
    }

    @Transactional(readOnly = true)
    public List<FeedbackResponse> getLatest() {
        return feedbackDao.getLatest();
    }

    @Transactional(readOnly = true)
    public List<FeedbackResponse> getOlder(int last) {
        return feedbackDao.getOlder(last);
    }

    @Transactional(readOnly = true)
    public AttachmentResponse getFile(UUID id) {
        return fileDao.get(id);
    }
}
