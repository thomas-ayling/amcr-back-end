package com.globallogic.amcr.persistence.dao.contactcomponent;

import com.globallogic.amcr.persistence.mapper.contactcomponent.FeedbackMapper;
import com.globallogic.amcr.persistence.dao.Dao;
import com.globallogic.amcr.persistence.model.contactcomponent.Feedback;
import com.globallogic.amcr.persistence.payload.contactcomponent.FeedbackResponse;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class FeedbackDao implements Dao<Feedback, FeedbackResponse> {
    final FeedbackMapper feedbackMapper;
    private final Logger Log = LoggerFactory.getLogger(FeedbackDao.class.getName());

    public FeedbackDao(FeedbackMapper feedbackMapper) {
        this.feedbackMapper = Assert.assertNull(feedbackMapper, "Feedback mapper cannot be null");
    }

    /**
     * @param feedback   the feedback object received from the client, no ID set
     * @param feedbackId the id for the feedback, to be set in the feedback object
     */
    @Override
    public Feedback save(Feedback feedback, UUID feedbackId) {
        try {
            feedback.setId(feedbackId);
            Log.trace("DAO saving feedback {}", feedback);
            feedbackMapper.save(feedback);
            return feedback;
        } catch (Exception e) {
            throw new RuntimeException("Error in FeedbackDao - could not save feedback", e);
        }
    }

    /**
     * @param id the id of the feedback to be retrieved from the database
     * @return returns the appropriate feedback entry
     */
    @Override
    public FeedbackResponse get(UUID id) {
        Log.trace("DAO requesting feedback with ID {}", id);
        return feedbackMapper.get(id);
    }

    /**
     * @return returns a list all the entries in the feedback table
     */
    @Override
    public List<FeedbackResponse> getAll() {
        Log.trace("DAO requesting all feedback");
        return feedbackMapper.getAll();
    }

    /**
     * @return returns a list of the last 10 entries in the feedback table
     */
    public List<FeedbackResponse> getLatest() {
        Log.trace("DAO requesting latest feedback");
        return feedbackMapper.getLatest();
    }

    /**
     * @param last the 'feedback order' of the last received feedback entry
     * @return returns the 10 entries that follow the 'last' entry
     */
    public List<FeedbackResponse> getOlder(int last) {
        Log.trace("DAO requesting 10 feedback entries older than entry {}", last);
        return feedbackMapper.getOlder(last);
    }
}
