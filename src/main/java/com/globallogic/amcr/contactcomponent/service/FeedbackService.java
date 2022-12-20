package com.globallogic.amcr.contactcomponent.service;

import com.globallogic.amcr.contactcomponent.persistence.model.Feedback;
import com.globallogic.amcr.contactcomponent.persistence.payload.FeedbackResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FeedbackService {
    /**
     * @param feedback   the feedback object with the data to be saved
     * @param attachment the attachment that needs to be saved belonging to the feedback
     * @return a response entity with the status of the request
     */
    ResponseEntity save(Feedback feedback, MultipartFile attachment);

    /**
     * @return a list of all entries in the feedback that also contains download links to attachments if applicable
     */
    List<FeedbackResponse> getAll();

    /**
     * @return a list of the 10 newest entries in the feedback table
     */
    List<FeedbackResponse> getLatest();

    /**
     * @param last the 'order' number of the last returned feedback entry
     * @return a list of 10 feedback entries that come after the 'last' entry
     */
    List<FeedbackResponse> getOlder(int last);
}
