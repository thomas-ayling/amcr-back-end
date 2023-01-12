package com.globallogic.amcr.attachment_component.service;

import com.globallogic.amcr.attachment_component.persistance.dao.AttachmentDAO;
import com.globallogic.amcr.attachment_component.persistance.payload.AttachmentMetadata;
import com.globallogic.amcr.attachment_component.persistance.payload.AttachmentResponse;
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

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentDAO attachmentDAO;

    public AttachmentServiceImpl(AttachmentDAO attachmentDAO) {
        this.attachmentDAO = attachmentDAO;
    }

    public ResponseEntity upload(MultipartFile attachment) {
        try {
            attachmentDAO.upload(attachment);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Resource> retrieve(UUID id) {
        try {
            AttachmentResponse attachmentResponse = attachmentDAO.retrieve(id);
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachmentResponse
                    .getContentType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; name=\""
                    + attachmentResponse.getName() + "\"")
                    .body(new ByteArrayResource(attachmentResponse
                            .getData()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional(readOnly = true)
    public List<AttachmentMetadata> retrieveAll() {
        return attachmentDAO.retrieveAll();
    }

    @Transactional
    public void delete(UUID id) {
       attachmentDAO.delete(id);
    }
}
