package com.globallogic.amcr.persistence.dao.contactcomponent;

import com.globallogic.amcr.mapper.contactcomponent.FileMapper;
import com.globallogic.amcr.persistence.dao.Dao;
import com.globallogic.amcr.persistence.model.contactcomponent.Attachment;
import com.globallogic.amcr.persistence.payload.contactcomponent.AttachmentMetadata;
import com.globallogic.amcr.persistence.payload.contactcomponent.AttachmentResponse;
import com.globallogic.amcr.utils.Assert;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@Repository
public class FileDao implements Dao<Attachment, AttachmentResponse> {
    private final FileMapper fileMapper;

    public FileDao(FileMapper fileMapper) {
        this.fileMapper = Assert.assertNotNull(fileMapper, "File mapper cannot be null");
    }

    /**
     * @param attachment attachment being received from the client
     * @param feedbackId the id of the feedback that the attachment belongs to (foreign key)
     */

    public Attachment save(Attachment attachment, UUID feedbackId) {
        try {
            // Generate UUID for the attachment
            UUID fileId = UUID.randomUUID();
            // Generate file download uri for the attachment
            attachment.setId(fileId);
            // Generate and set the attachment's download uri
            attachment.setDownloadUri(ServletUriComponentsBuilder.fromCurrentContextPath().path("/feedback/get-file/").path(fileId.toString()).toUriString());
            // Set feedback id
            attachment.setFeedbackId(feedbackId);
            // Save attachment
            fileMapper.save(attachment);
            return attachment;
        } catch (Exception e) {
            throw new RuntimeException("Could not save attachment", e);
        }
    }

    /**
     * @param id the id of the attachment to be downloaded
     * @return returns the appropriate attachment from the mapper
     */
    public AttachmentResponse get(UUID id) {
        return fileMapper.get(id);
    }

    /**
     * @return returns a list of all the attachment in the attachment table
     */
    public List<AttachmentResponse> getAll() {
        return fileMapper.getAll();
    }

    /**
     * @param feedbackId the id of the foreign key for the requested metadata
     * @return returns an attachment metadata object as received from the database
     */
    public AttachmentMetadata getAttachmentMetadata(UUID feedbackId) {
        return fileMapper.getAttachmentMetadata(feedbackId);
    }
}
