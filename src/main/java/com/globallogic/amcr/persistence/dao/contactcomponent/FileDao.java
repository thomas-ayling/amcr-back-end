package com.globallogic.amcr.persistence.dao.contactcomponent;

import com.globallogic.amcr.mapper.contactcomponent.FileMapper;
import com.globallogic.amcr.persistence.dao.Dao;
import com.globallogic.amcr.persistence.model.contactcomponent.Attachment;
import com.globallogic.amcr.persistence.payload.contactcomponent.AttachmentMetadata;
import com.globallogic.amcr.persistence.payload.contactcomponent.AttachmentResponse;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@Repository
public class FileDao implements Dao<Attachment, AttachmentResponse> {
    private final FileMapper fileMapper;

    public FileDao(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    /**
     * @param attachment attachment being received from the client
     * @param feedbackId the id of the feedback that the attachment belongs to (foreign key)
     */

    public void save(Attachment attachment, UUID feedbackId) {
        // Generate UUID for the attachment
        UUID fileId = UUID.randomUUID();
        // Generate file download uri for the attachment
        attachment.setId(fileId);
        // Generate and set the attachment's download uri
        attachment.setDownloadUri(ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/download/").path(fileId.toString()).toUriString());
        // Set feedback id
        attachment.setFeedbackId(feedbackId);
        // Save attachment
        fileMapper.save(attachment);
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
