package com.globallogic.amcr.persistence.dao;

import com.globallogic.amcr.persistence.model.Feedback;
import com.globallogic.amcr.persistence.payload.contactcomponent.FeedbackResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Dao<T, R> {
    void save(T t, UUID id);

    R get(UUID id);

    List<R> getAll();
}
