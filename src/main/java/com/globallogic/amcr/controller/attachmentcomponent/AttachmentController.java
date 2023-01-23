package com.globallogic.amcr.controller.attachmentcomponent;

import com.globallogic.amcr.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.service.attachmentcomponent.AttachmentService;

import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/attachments")
@CrossOrigin
public class AttachmentController {
    private final AttachmentService attachmentService;
    private final Logger LOG = LoggerFactory.getLogger(AttachmentController.class);

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = Assert.assertNotNull(attachmentService, "Attachment service cannot be null");
    }

    @PostMapping("/")
    public ResponseEntity<Attachment> save(@RequestBody Attachment attachment, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException(errors.toString());
        }
        LOG.debug("Controller requesting a new attachment to be saved with id {}", attachment.getId());
        Attachment incomingAttachment = attachmentService.save(attachment);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(incomingAttachment.getId()).toUri()).body(incomingAttachment);
    }

    @GetMapping(value = "/binary/{id}")
    public ResponseEntity<byte[]> getBytes(@PathVariable UUID id) {
        Attachment attachmentByte = attachmentService.get(id);
        if (attachmentByte == null) {
            LOG.debug("Controller requesting attachment with ID that does not exist {}", id);
            return ResponseEntity.notFound().build();
        }
        byte[] bytes = attachmentByte.getContent();
        if (bytes != null && bytes.length > 0) {
            LOG.debug("Controller requesting attachment with ID {} that has no content", id);
            return ResponseEntity.ok().body(bytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        LOG.debug("Controller requesting to delete attachment with ID {}", id);
        attachmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> get(@PathVariable UUID id, @RequestBody byte[] content) {
        Attachment attachment = new Attachment();
        attachment.setContent(content);
        LOG.debug("Controller requesting to update with ID {}", id);
        return ResponseEntity.accepted().body(attachmentService.update(attachment, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getMedia(@PathVariable UUID id) {
        Attachment attachment = attachmentService.get(id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType
                (attachment.getType())).header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + attachment.getName()
                        + "\"").body(new ByteArrayResource(attachment.getContent()));
    }
}