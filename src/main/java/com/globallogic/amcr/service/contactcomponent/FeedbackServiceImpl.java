package com.globallogic.amcr.service.contactcomponent;

import com.globallogic.amcr.persistence.dao.contactcomponent.FeedbackDao;
import com.globallogic.amcr.persistence.model.contactcomponent.Feedback;
import com.globallogic.amcr.persistence.payload.contactcomponent.FeedbackResponse;
import io.github.resilience4j.core.IntervalFunction;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackDao feedbackDao;
    private final FileServiceImpl fileServiceImpl;
    private final EmailServiceImpl emailServiceImpl;

    public FeedbackServiceImpl(EmailServiceImpl emailServiceImpl, FileServiceImpl fileServiceImpl, FeedbackDao feedbackDao) {
        this.emailServiceImpl = emailServiceImpl;
        this.feedbackDao = feedbackDao;
        this.fileServiceImpl = fileServiceImpl;
    }

    @Transactional
    public ResponseEntity save(Feedback feedback, MultipartFile attachment) {
        RetryConfig config = RetryConfig.custom().intervalFunction(IntervalFunction.ofExponentialBackoff()).failAfterMaxAttempts(true).build();
        RetryRegistry registry = RetryRegistry.of(config);

        try {
            UUID feedbackId = UUID.randomUUID();
            feedbackDao.save(feedback, feedbackId);
            if (attachment != null) fileServiceImpl.save(attachment, feedbackId);
            emailServiceImpl.sendMail(feedback, feedbackId);
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
