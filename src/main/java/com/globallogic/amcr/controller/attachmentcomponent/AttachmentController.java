package com.globallogic.amcr.controller.attachmentcomponent;

import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentMetadata;
import com.globallogic.amcr.service.attachmentcomponent.AttachmentService;
import com.globallogic.amcr.service.attachmentcomponent.AttachmentServiceImpl;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/attachments")
@CrossOrigin
public class AttachmentController {
    public final Logger Log = LoggerFactory.getLogger(AttachmentController.class.getName());
    private final AttachmentServiceImpl attachmentServiceImpl;
    private final AttachmentService attachmentService;

    public AttachmentController(AttachmentServiceImpl attachmentServiceImpl, AttachmentService attachmentService) {
        this.attachmentServiceImpl = attachmentServiceImpl;
        this.attachmentService = attachmentService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity uploadAttachment(@RequestPart(value = "attachment") MultipartFile attachment) {
        Log.debug("Saving a new attachment {}", attachment);
        return attachmentServiceImpl.upload(attachment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> retrieveAttachment(@PathVariable UUID id) {
        Log.debug("Requesting an attachment with ID {}", id);
        return attachmentServiceImpl.retrieve(id);
    }

    @GetMapping("/retrieve-all-metadata")
    public List<AttachmentMetadata> retrieveAllAttachments() {
        Log.debug("Requesting all attachments");
        return attachmentServiceImpl.retrieveAll();
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteAttachment(@PathVariable UUID id) {
        try {
            Log.debug("Requesting to delete attachment with ID {}", id);
            attachmentService.delete(id);
            return ResponseEntity.ok().body("File with ID: " + id + " was deleted.");
        } catch (Exception e) {
            throw new RuntimeException("Controller could not delete attachment", e);
        }
    }
}
