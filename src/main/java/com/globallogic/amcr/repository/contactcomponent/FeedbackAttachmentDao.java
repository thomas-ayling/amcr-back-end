package com.globallogic.amcr.repository.contactcomponent;

import com.globallogic.amcr.repository.Dao;
import com.globallogic.amcr.model.contactcomponent.FeedbackAttachment;
import com.globallogic.amcr.model.contactcomponent.FeedbackAttachmentMetadata;
import com.globallogic.amcr.model.contactcomponent.FeedbackAttachmentResponse;

import java.util.UUID;

public interface FeedbackAttachmentDao extends Dao<FeedbackAttachment, FeedbackAttachmentResponse> {

    /**
     * @param feedbackId The id of the foreign key for the requested metadata
     * @return Returns an attachment metadata object as received from the database
     */
    FeedbackAttachmentMetadata getAttachmentMetadata(UUID feedbackId);
}
