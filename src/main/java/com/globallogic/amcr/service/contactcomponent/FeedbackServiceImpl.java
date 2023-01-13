package com.globallogic.amcr.service.contactcomponent;

import com.globallogic.amcr.controller.casestudies.CaseStudyController;
import com.globallogic.amcr.persistence.dao.contactcomponent.FeedbackDao;
import com.globallogic.amcr.persistence.dao.contactcomponent.FeedbackAttachmentDao;
import com.globallogic.amcr.persistence.model.contactcomponent.FeedbackAttachment;
import com.globallogic.amcr.persistence.model.contactcomponent.Feedback;
import com.globallogic.amcr.persistence.payload.contactcomponent.AttachmentResponse;
import com.globallogic.amcr.persistence.payload.contactcomponent.FeedbackResponse;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final Logger Log = LoggerFactory.getLogger(CaseStudyController.class.getName());
    private final FeedbackDao feedbackDao;
    private final FeedbackAttachmentDao feedbackAttachmentDao;
    private final EmailService emailService;

    public FeedbackServiceImpl(FeedbackDao feedbackDao, FeedbackAttachmentDao feedbackAttachmentDao, EmailService emailService) {
        this.feedbackDao = Assert.assertNull(feedbackDao, "Feedback DAO cannot be null");
        this.feedbackAttachmentDao = Assert.assertNull(feedbackAttachmentDao, "File DAO cannot be null");
        this.emailService = Assert.assertNull(emailService, "Email service cannot be null");
    }

    @Transactional
    public Feedback save(Feedback feedback, FeedbackAttachment feedbackAttachment) {
        try {
            UUID feedbackId = UUID.randomUUID();
            Log.debug("Service saving feedback");
            Feedback returnedFeedback = feedbackDao.save(feedback, feedbackId);
            if (feedbackAttachment != null) {
                Log.debug("Service saving feedbackAttachment");
                feedbackAttachmentDao.save(feedbackAttachment, feedbackId);
            }
            Log.debug("Service sending email");
            emailService.sendMail(feedback, feedbackId);
            return returnedFeedback;
        } catch (Exception e) {
            throw new RuntimeException("Error saving feedback and feedbackAttachment to database", e);
        }
    }

    @Transactional(readOnly = true)
    public List<FeedbackResponse> getAll() {
        Log.debug("Service requesting all feedback entries");
        return feedbackDao.getAll();
    }

    @Transactional(readOnly = true)
    public List<FeedbackResponse> getLatest() {
        Log.debug("Service requesting latest feedback");
        return feedbackDao.getLatest();
    }

    @Transactional(readOnly = true)
    public List<FeedbackResponse> getOlder(int last) {
        Log.debug("Service requesting 10 older feedback entries");
        return feedbackDao.getOlder(last);
    }

    @Transactional(readOnly = true)
    public AttachmentResponse getFile(UUID id) {
        Log.debug("Service requesting attachment with ID {}", id);
        return feedbackAttachmentDao.get(id);
    }
}
