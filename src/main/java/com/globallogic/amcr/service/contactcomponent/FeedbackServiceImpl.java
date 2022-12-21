package com.globallogic.amcr.service.contactcomponent;

import com.globallogic.amcr.persistence.dao.contactcomponent.FeedbackDao;
import com.globallogic.amcr.persistence.model.contactcomponent.Attachment;
import com.globallogic.amcr.persistence.model.contactcomponent.Feedback;
import com.globallogic.amcr.persistence.payload.contactcomponent.FeedbackResponse;
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

    public FeedbackServiceImpl(FeedbackDao feedbackDao, FileServiceImpl fileServiceImpl, EmailServiceImpl emailServiceImpl) {
        this.feedbackDao = feedbackDao;
        this.fileServiceImpl = fileServiceImpl;
        this.emailServiceImpl = emailServiceImpl;
    }

    @Transactional
    public ResponseEntity save(Feedback feedback, Attachment attachment) {
        try {
            UUID feedbackId = UUID.randomUUID();
            // send mail
            emailServiceImpl.sendMail(feedback, feedbackId);
            // save feedback to db
            feedbackDao.save(feedback, feedbackId);
            // save attachment if exists
            if (attachment != null) {
                fileServiceImpl.save(attachment, feedbackId);
            }
            // return ok
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // return internal server error if an exception is caught
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
