package com.globallogic.amcr.service.attachmentcomponent;

import com.globallogic.amcr.persistence.dao.attachmentcomponent.AttachmentDAO;
import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentMetadata;
import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentResponse;
import com.globallogic.amcr.utils.Assert;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    public  final Logger Log = LoggerFactory.getLogger(AttachmentServiceImpl.class);
    private final AttachmentDAO attachmentDAO;

    public AttachmentServiceImpl(AttachmentDAO attachmentDAO) {
        this.attachmentDAO = Assert.assertNull(attachmentDAO, "AttachmentDAO is not present");
    }

    @Transactional
    public ResponseEntity create(MultipartFile attachment) {
        try {
            Log.debug("Attempting to upload an attachment {}", attachment);
            attachmentDAO.save(attachment);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Resource> get(UUID id) {
        Assert.assertNull(id, "Attachment ID cannot be null");
        try {
            Log.debug("Attempting to retrieve an attachment with ID {}", id);
            AttachmentResponse attachmentResponse = attachmentDAO.get(id);
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachmentResponse
                            .getContentType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; name=\""
                            + attachmentResponse.getName() + "\"")
                    .body(new ByteArrayResource(attachmentResponse
                            .getData()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Transactional(readOnly = true)
    public List<AttachmentMetadata> getAll() {
        Log.debug("Attempting to retrieve all metadata for all attachments in the database");
        return attachmentDAO.getALl();
    }

    @Transactional
    public void delete(UUID id) {
        Log.debug("Attempting to delete an attachment with a ID {}", id);
        attachmentDAO.delete(id);
    }
}
