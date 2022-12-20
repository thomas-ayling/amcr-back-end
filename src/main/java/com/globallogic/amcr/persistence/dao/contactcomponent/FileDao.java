package com.globallogic.amcr.persistence.dao.contactcomponent;

import com.globallogic.amcr.mapper.contactcomponent.FileMapper;
import com.globallogic.amcr.persistence.model.contactcomponent.Attachment;
import com.globallogic.amcr.persistence.payload.contactcomponent.AttachmentMetadata;
import com.globallogic.amcr.persistence.payload.contactcomponent.AttachmentResponse;
import com.globallogic.amcr.persistence.dao.Dao;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
public class FileDao implements Dao<MultipartFile, AttachmentResponse> {
    private final FileMapper fileMapper;

    public FileDao(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    /**
     * @param incomingAttachment the attachment being received from the client
     * @param feedbackId         the id of the feedback that the attachment belongs to (foreign key)
     */

    public void save(MultipartFile incomingAttachment, UUID feedbackId) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(incomingAttachment.getOriginalFilename()));
        try {
            // Generate random UUID for the file
            UUID fileId = UUID.randomUUID();
            // Generate file download uri for the attachment
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/download/").path(fileId.toString()).toUriString();
            // Create new Attachment object with generated params
            Attachment attachment = new Attachment(fileId, fileName, incomingAttachment.getContentType(), incomingAttachment.getSize(), incomingAttachment.getBytes(), fileDownloadUri, feedbackId);
            fileMapper.save(attachment);
        } catch (IOException ioe) {
            throw new RuntimeException(fileName + " could not be saved.", ioe);
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
