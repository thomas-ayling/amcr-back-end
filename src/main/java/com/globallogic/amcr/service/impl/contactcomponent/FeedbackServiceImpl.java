package com.globallogic.amcr.service.impl.contactcomponent;

import com.globallogic.amcr.controller.casestudies.CaseStudyController;
import com.globallogic.amcr.model.contactcomponent.Feedback;
import com.globallogic.amcr.model.contactcomponent.FeedbackAttachment;
import com.globallogic.amcr.model.contactcomponent.FeedbackAttachmentResponse;
import com.globallogic.amcr.repository.contactcomponent.FeedbackAttachmentDao;
import com.globallogic.amcr.repository.contactcomponent.FeedbackDao;
import com.globallogic.amcr.service.contactcomponent.EmailService;
import com.globallogic.amcr.service.contactcomponent.FeedbackService;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final Logger log = LoggerFactory.getLogger(CaseStudyController.class);
    private final FeedbackDao feedbackDao;
    private final FeedbackAttachmentDao feedbackAttachmentDao;
    private final EmailService emailService;

    public FeedbackServiceImpl(FeedbackDao feedbackDao, FeedbackAttachmentDao feedbackAttachmentDao, EmailService emailService) {
        this.feedbackDao = Assert.assertNotNull(feedbackDao, "Feedback DAO cannot be null");
        this.feedbackAttachmentDao = Assert.assertNotNull(feedbackAttachmentDao, "File DAO cannot be null");
        this.emailService = Assert.assertNotNull(emailService, "Email service cannot be null");
    }

    @Override
    @Transactional
    public Feedback save(Feedback feedback, FeedbackAttachment feedbackAttachment) {
        Assert.assertNotNull(feedback, "Feedback cannot be null");
        try {
            UUID feedbackId = UUID.randomUUID();
            log.debug("Service saving feedback");
            Feedback createdFeedback = feedbackDao.save(feedback, feedbackId);
            if (feedbackAttachment != null) {
                log.debug("Service saving feedbackAttachment");
                feedbackAttachmentDao.save(feedbackAttachment, feedbackId);
            }
            log.debug("Service sending email");
            emailService.sendMail(feedback, feedbackId);
            log.debug("Email has sent from feedback service");
            return createdFeedback;
        } catch (Exception e) {
            throw new RuntimeException("Error saving feedback and feedbackAttachment to database", e);
        }
    }

    @Override
    @Transactional
    public Feedback get(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null to request feedback");
        log.debug("Service requesting feedback with ID {}", id);
        return feedbackDao.get(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Feedback> getAll() {
        log.debug("Service requesting all feedback entries");
        return feedbackDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Feedback> getLatest() {
        log.debug("Service requesting latest feedback");
        return feedbackDao.getLatest();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Feedback> getOlder(int last) {
        Assert.assertNotNull(last, "Last cannot be null");
        log.debug("Service requesting 10 older feedback entries");
        return feedbackDao.getOlder(last);
    }

    @Override
    @Transactional(readOnly = true)
    public FeedbackAttachmentResponse getAttachment(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null for file request");
        log.debug("Service requesting attachment with ID {}", id);
        return feedbackAttachmentDao.get(id);
    }

    @Override
    public int getCount() {
        return feedbackDao.getCount();
    }


}
