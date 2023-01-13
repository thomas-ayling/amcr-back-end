package com.globallogic.amcr.service.contactcomponent;

import com.globallogic.amcr.persistence.model.contactcomponent.*;
import com.globallogic.amcr.persistence.model.contactcomponent.FeedbackAttachment;
import com.globallogic.amcr.persistence.model.contactcomponent.FeedbackAttachmentResponse;

import java.util.List;
import java.util.UUID;

public interface FeedbackService {

    /**
     * @param feedback           the feedback object with the data to be saved
     * @param feedbackAttachment the feedbackAttachment that needs to be saved belonging to the feedback
     * @return a response entity with the status of the request
     */
    Feedback save(Feedback feedback, FeedbackAttachment feedbackAttachment);

    /**
     * @param id the ID of the requested feedback entry
     * @return returns the requested entry
     */
    Feedback get(UUID id);

    /**
     * @return a list of all entries in the feedback that also contains download links to attachments if applicable
     */
    List<Feedback> getAll();

    /**
     * @return a list of the 10 newest entries in the feedback table
     */
    List<Feedback> getLatest();

    /**
     * @param last the 'order' number of the last returned feedback entry
     * @return a list of 10 feedback entries that come after the 'last' entry
     */
    List<Feedback> getOlder(int last);

    /**
     * @param id the id of the requested attachment
     * @return a response entity with the attachment data embedded
     */
    FeedbackAttachmentResponse getFile(UUID id);
}
