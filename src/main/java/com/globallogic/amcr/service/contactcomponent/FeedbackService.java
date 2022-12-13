package com.globallogic.amcr.service.contactcomponent;

import com.globallogic.amcr.persistence.dao.contactcomponent.FeedbackDao;
import com.globallogic.amcr.persistence.dao.contactcomponent.FileDao;
import com.globallogic.amcr.persistence.model.Feedback;
import com.globallogic.amcr.persistence.payload.contactcomponent.FeedbackResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class FeedbackService {

    private final FeedbackDao feedbackDao;
    private final FileService fileService;
    private final EmailService emailService;

    public FeedbackService(EmailService emailService, FileService fileService, FeedbackDao feedbackDao) {
        this.emailService = emailService;
        this.feedbackDao = feedbackDao;
        this.fileService = fileService;
    }

    @Transactional
    public ResponseEntity save(Feedback feedback, MultipartFile attachment) {
        try {
            UUID feedbackId = UUID.randomUUID();
            feedbackDao.save(feedback, feedbackId);
            if (attachment != null) fileService.save(attachment, feedbackId);
            emailService.sendMail(feedback, feedbackId, 0);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
}
