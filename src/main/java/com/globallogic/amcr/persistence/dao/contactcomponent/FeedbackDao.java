package com.globallogic.amcr.persistence.dao.contactcomponent;

import com.globallogic.amcr.mapper.contactcomponent.FeedbackMapper;
import com.globallogic.amcr.mapper.contactcomponent.FileMapper;
import com.globallogic.amcr.persistence.dao.Dao;
import com.globallogic.amcr.persistence.model.Feedback;
import com.globallogic.amcr.persistence.payload.contactcomponent.FeedbackResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class FeedbackDao implements Dao<Feedback, FeedbackResponse> {
    FeedbackMapper feedbackMapper;
    FileMapper fileMapper;

    public FeedbackDao(FeedbackMapper feedbackMapper, FileMapper fileMapper) {
        this.feedbackMapper = feedbackMapper;
        this.fileMapper = fileMapper;
    }

    @Override
    public void save(Feedback feedback, UUID feedbackId) {
        try {
            feedback.setId(feedbackId);
            feedbackMapper.save(feedback);
        } catch (Exception e) {
            throw new RuntimeException("Could not save feedback", e);
        }
    }

    @Override
    public FeedbackResponse get(UUID id) { return feedbackMapper.get(id); }

    @Override
    public List<FeedbackResponse> getAll() {
        return feedbackMapper.getAll();
    }

    public List<FeedbackResponse> getLatest() {
        return feedbackMapper.getLatest();
    }

    public List<FeedbackResponse> getOlder(int last) {
        return feedbackMapper.getOlder(last);
    }
}
