package com.globallogic.amcr.repository.impl.contactcomponent;

import com.globallogic.amcr.model.contactcomponent.FeedbackAttachment;
import com.globallogic.amcr.model.contactcomponent.FeedbackAttachmentMetadata;
import com.globallogic.amcr.model.contactcomponent.FeedbackAttachmentResponse;
import com.globallogic.amcr.repository.contactcomponent.FeedbackAttachmentDao;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@Repository
public class FeedbackAttachmentDaoImpl implements FeedbackAttachmentDao {
    private final Logger log = LoggerFactory.getLogger(FeedbackAttachmentDaoImpl.class);
    private final FeedbackAttachmentMapper feedbackAttachmentMapper;

    public FeedbackAttachmentDaoImpl(FeedbackAttachmentMapper feedbackAttachmentMapper) {
        this.feedbackAttachmentMapper = Assert.assertNotNull(feedbackAttachmentMapper, "File mapper cannot be null");
    }

    @Override
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
            log.trace("DAO saving feedback attachment {}", feedbackAttachment);
            // Save feedbackAttachment
            feedbackAttachmentMapper.save(feedbackAttachment);
            // Return feedbackAttachment
            return feedbackAttachment;
        } catch (Exception e) {
            throw new RuntimeException("Could not save feedback attachment", e);
        }
    }

    @Override
    public FeedbackAttachmentResponse get(UUID id) {
        log.trace("DAO requesting attachment with ID {}", id);
        return feedbackAttachmentMapper.get(id);
    }

    @Override
    public List<FeedbackAttachmentResponse> getAll() {
        log.trace("DAO requesting all attachments");
        return feedbackAttachmentMapper.getAll();
    }

    @Override
    public FeedbackAttachmentMetadata getAttachmentMetadata(UUID feedbackId) {
        log.trace("DAO requesting metadata for attachment with ID {}", feedbackId);
        return feedbackAttachmentMapper.getAttachmentMetadata(feedbackId);
    }
}
