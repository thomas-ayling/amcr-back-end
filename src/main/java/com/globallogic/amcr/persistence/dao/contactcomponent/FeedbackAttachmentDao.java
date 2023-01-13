package com.globallogic.amcr.persistence.dao.contactcomponent;

import com.globallogic.amcr.persistence.mapper.contactcomponent.FeedbackAttachmentMapper;
import com.globallogic.amcr.persistence.dao.Dao;
import com.globallogic.amcr.persistence.model.contactcomponent.FeedbackAttachment;
import com.globallogic.amcr.persistence.payload.contactcomponent.AttachmentMetadata;
import com.globallogic.amcr.persistence.payload.contactcomponent.AttachmentResponse;
import com.globallogic.amcr.utils.Assert;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@Repository
public class FeedbackAttachmentDao implements Dao<FeedbackAttachment, AttachmentResponse> {
    private final FeedbackAttachmentMapper feedbackAttachmentMapper;

    public FeedbackAttachmentDao(FeedbackAttachmentMapper feedbackAttachmentMapper) {
        this.feedbackAttachmentMapper = Assert.assertNull(feedbackAttachmentMapper, "File mapper cannot be null");
    }

    /**
     * @param feedbackAttachment feedbackAttachment being received from the client
     * @param feedbackId the id of the feedback that the feedbackAttachment belongs to (foreign key)
     */

    public FeedbackAttachment save(FeedbackAttachment feedbackAttachment, UUID feedbackId) {
        try {
            // Generate UUID for the feedbackAttachment
            UUID attachmentId = UUID.randomUUID();
            // Generate attachment download uri for the feedbackAttachment
            feedbackAttachment.setId(attachmentId);
            // Generate and set the feedbackAttachment's download uri
            feedbackAttachment.setDownloadUri(ServletUriComponentsBuilder.fromCurrentContextPath().path("/feedback/attachment/").path(attachmentId.toString()).toUriString());
            // Set feedback id
            feedbackAttachment.setFeedbackId(feedbackId);
            // Save feedbackAttachment
            feedbackAttachmentMapper.save(feedbackAttachment);
            // Return feedbackAttachment
            return feedbackAttachment;
        } catch (Exception e) {
            throw new RuntimeException("Could not save feedbackAttachment", e);
        }
    }

    /**
     * @param id the id of the attachment to be downloaded
     * @return returns the appropriate attachment from the mapper
     */
    public AttachmentResponse get(UUID id) {
        return feedbackAttachmentMapper.get(id);
    }

    /**
     * @return returns a list of all the attachment in the attachment table
     */
    public List<AttachmentResponse> getAll() {
        return feedbackAttachmentMapper.getAll();
    }

    /**
     * @param feedbackId the id of the foreign key for the requested metadata
     * @return returns an attachment metadata object as received from the database
     */
    public AttachmentMetadata getAttachmentMetadata(UUID feedbackId) {
        return feedbackAttachmentMapper.getAttachmentMetadata(feedbackId);
    }
}
