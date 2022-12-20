package com.globallogic.amcr.service.contactcomponent;

import com.globallogic.amcr.persistence.dao.contactcomponent.FeedbackDao;
import com.globallogic.amcr.persistence.model.contactcomponent.Feedback;
import com.globallogic.amcr.persistence.payload.contactcomponent.FeedbackResponse;
import io.github.resilience4j.core.IntervalFunction;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackDao feedbackDao;
    private final FileServiceImpl fileServiceImpl;
    private final EmailService emailService;
    private final EmailServiceImpl emailServiceImpl;

    public FeedbackServiceImpl(FeedbackDao feedbackDao, FileServiceImpl fileServiceImpl, EmailService emailService, EmailServiceImpl emailServiceImpl) {
        this.feedbackDao = feedbackDao;
        this.fileServiceImpl = fileServiceImpl;
        this.emailService = emailService;
        this.emailServiceImpl = emailServiceImpl;
    }

    @Transactional
    public ResponseEntity save(Feedback feedback, MultipartFile attachment) {
        RetryConfig config = RetryConfig.custom().intervalFunction(IntervalFunction.ofExponentialBackoff()).failAfterMaxAttempts(true).build();
        RetryRegistry registry = RetryRegistry.of(config);
        Retry retry = registry.retry("emailService", config);

        UUID feedbackId = UUID.randomUUID();
        feedbackDao.save(feedback, feedbackId);
        if (attachment != null) fileServiceImpl.save(attachment, feedbackId);

        Supplier sendEmail = () -> emailService.sendMail(feedback, feedbackId);
        Supplier retryingEmailService = Retry.decorateSupplier(retry, sendEmail);
        retryingEmailService.get();

        return new ResponseEntity<>(HttpStatus.OK);
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
