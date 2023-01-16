package com.globallogic.amcr.service.contactcomponent;

import com.globallogic.amcr.model.contactcomponent.Feedback;
import com.globallogic.amcr.model.contactcomponent.FeedbackAttachment;
import com.globallogic.amcr.model.contactcomponent.FeedbackAttachmentResponse;

import java.util.List;
import java.util.UUID;

public interface FeedbackService {

    /**
     * @param feedback           The feedback object with the data to be saved
     * @param feedbackAttachment The feedbackAttachment that needs to be saved belonging to the feedback
     * @return a response entity with the status of the request
     */
    Feedback save(Feedback feedback, FeedbackAttachment feedbackAttachment);

    /**
     * @param id The ID of the requested feedback entry
     * @return Returns the requested entry
     */
    Feedback get(UUID id);

    /**
     * @return Returns a list of all entries in the feedback that also contains download links to attachments if applicable
     */
    List<Feedback> getAll();

    /**
     * @return Returns a list of the 10 newest entries in the feedback table
     */
    List<Feedback> getLatest();

    /**
     * @param last The 'order' number of the last returned feedback entry
     * @return Returns a list of 10 feedback entries that come after the 'last' entry
     */
    List<Feedback> getOlder(int last);

    /**
     * @param id The id of the requested attachment
     * @return a Response entity with the attachment data embedded
     */
    FeedbackAttachmentResponse getAttachment(UUID id);
}
