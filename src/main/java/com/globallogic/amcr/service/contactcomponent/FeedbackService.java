package com.globallogic.amcr.service.contactcomponent;

import com.globallogic.amcr.model.contactcomponent.Feedback;
import com.globallogic.amcr.model.contactcomponent.FeedbackAttachment;
import com.globallogic.amcr.model.contactcomponent.FeedbackAttachmentResponse;
import com.globallogic.amcr.service.Service;

import java.util.List;
import java.util.UUID;

public interface FeedbackService extends Service<Feedback> {
    // Extra save and get methods

    /** Sends a Feedback object to the FeedbackDao and a FeedbackAttachment object to the FeedbackAttachmentDao
     *
     * @param feedback The feedback object to be saved
     * @param feedbackAttachment The feedback attachment object to be saved
     * @return Returns the complete Feedback object as saved to the database
     */
    Feedback save(Feedback feedback, FeedbackAttachment feedbackAttachment);

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
     * @param id the id of the requested attachment
     * @return a response entity with the attachment data embedded
     */
    FeedbackAttachmentResponse getAttachment(UUID id);
}
