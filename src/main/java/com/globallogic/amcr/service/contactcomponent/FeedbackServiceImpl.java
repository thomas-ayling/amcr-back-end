package com.globallogic.amcr.service.contactcomponent;

import com.globallogic.amcr.controller.casestudies.CaseStudyController;
import com.globallogic.amcr.persistence.dao.contactcomponent.FeedbackDao;
import com.globallogic.amcr.persistence.dao.contactcomponent.FeedbackAttachmentDao;
import com.globallogic.amcr.persistence.model.contactcomponent.FeedbackAttachment;
import com.globallogic.amcr.persistence.model.contactcomponent.Feedback;
import com.globallogic.amcr.persistence.model.contactcomponent.FeedbackAttachmentResponse;
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
        Assert.assertNull(feedback, "Feedback cannot be null");
        try {
            UUID feedbackId = UUID.randomUUID();
            Log.debug("Service saving feedback");
            Feedback createdFeedback = feedbackDao.save(feedback, feedbackId);
            if (feedbackAttachment != null) {
                Log.debug("Service saving feedbackAttachment");
                feedbackAttachmentDao.save(feedbackAttachment, feedbackId);
            }
            Log.debug("Service sending email");
            emailService.sendMail(feedback, feedbackId);
            return createdFeedback;
        } catch (Exception e) {
            throw new RuntimeException("Error saving feedback and feedbackAttachment to database", e);
        }
    }

    @Transactional
    public Feedback get(UUID id) {
        Assert.assertNull(id, "ID cannot be null to request feedback");
        Log.debug("Service requesting feedback with ID {}", id);
        return feedbackDao.get(id);
    }

    @Transactional(readOnly = true)
    public List<Feedback> getAll() {
        Log.debug("Service requesting all feedback entries");
        return feedbackDao.getAll();
    }

    @Transactional(readOnly = true)
    public List<Feedback> getLatest() {
        Log.debug("Service requesting latest feedback");
        return feedbackDao.getLatest();
    }

    @Transactional(readOnly = true)
    public List<Feedback> getOlder(int last) {
        Assert.assertNull(last, "Last cannot be null");
        Log.debug("Service requesting 10 older feedback entries");
        return feedbackDao.getOlder(last);
    }

    @Transactional(readOnly = true)
    public FeedbackAttachmentResponse getFile(UUID id) {
        Assert.assertNull(id, "ID cannot be null for file request");
        Log.debug("Service requesting attachment with ID {}", id);
        return feedbackAttachmentDao.get(id);
    }
}
