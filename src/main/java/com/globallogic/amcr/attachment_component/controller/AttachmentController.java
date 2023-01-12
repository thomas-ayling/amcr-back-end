package com.globallogic.amcr.attachment_component.controller;

import com.globallogic.amcr.attachment_component.persistance.payload.AttachmentMetadata;
import com.globallogic.amcr.attachment_component.service.AttachmentService;
import com.globallogic.amcr.attachment_component.service.AttachmentServiceImpl;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/attachments")
@CrossOrigin(origins = "*")
public class AttachmentController {

    private final AttachmentServiceImpl attachmentServiceImpl;
    private final AttachmentService attachmentService;

    public AttachmentController(AttachmentServiceImpl attachmentServiceImpl, AttachmentService attachmentService) {
        this.attachmentServiceImpl = attachmentServiceImpl;
        this.attachmentService = attachmentService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity uploadAttachment(@RequestPart(value = "attachment") MultipartFile attachment) {
        return attachmentServiceImpl.upload(attachment);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<Resource> retrieveAttachment(@PathVariable UUID id) {
        return attachmentServiceImpl.retrieve(id);
    }

    @GetMapping("/retrieve-all-metadata")
    public List<AttachmentMetadata> retrieveAllAttachments() {
        return attachmentServiceImpl.retrieveAll();
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteAttachment(@PathVariable UUID id) {
        try {
            attachmentService.delete(id);
            return ResponseEntity.ok().body("File with ID: " +id + " was deleted.");
        } catch (Exception e) {
            throw new RuntimeException("Controller could not delete attachment", e);
        }
    }
}
