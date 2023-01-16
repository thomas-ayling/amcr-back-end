package com.globallogic.amcr.repository.impl.contactcomponent;

import com.globallogic.amcr.repository.contactcomponent.FeedbackDao;
import com.globallogic.amcr.model.contactcomponent.Feedback;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class FeedbackDaoImpl implements FeedbackDao {
    final FeedbackMapper feedbackMapper;
    private final Logger Log = LoggerFactory.getLogger(FeedbackDaoImpl.class);

    public FeedbackDaoImpl(FeedbackMapper feedbackMapper) {
        this.feedbackMapper = Assert.assertNotNull(feedbackMapper, "Feedback mapper cannot be null");
    }

    @Override
    public Feedback save(Feedback feedback, UUID feedbackId) {
        try {
            feedback.setId(feedbackId);
            Log.trace("DAO saving feedback {}", feedback);
            feedbackMapper.save(feedback);
            return feedback;
        } catch (Exception e) {
            throw new RuntimeException("Error in FeedbackDaoImpl - could not save feedback", e);
        }
    }

    @Override
    public Feedback get(UUID id) {
        Log.trace("DAO requesting feedback with ID {}", id);
        return feedbackMapper.get(id);
    }

    @Override
    public List<Feedback> getAll() {
        Log.trace("DAO requesting all feedback");
        return feedbackMapper.getAll();
    }

    // Extra get methods

    @Override
    public List<Feedback> getLatest() {
        Log.trace("DAO requesting latest feedback");
        return feedbackMapper.getLatest();
    }

    public List<Feedback> getOlder(int last) {
        Log.trace("DAO requesting 10 feedback entries older than entry {}", last);
        return feedbackMapper.getOlder(last);
    }
}
