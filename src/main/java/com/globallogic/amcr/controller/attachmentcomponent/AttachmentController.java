package com.globallogic.amcr.controller.attachmentcomponent;

import com.globallogic.amcr.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.service.attachmentcomponent.AttachmentService;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    private final AttachmentService attachmentService;
    private final Logger LOG = LoggerFactory.getLogger(AttachmentController.class);

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = Assert.assertNotNull(attachmentService, "Attachment service cannot be null");
    }

    @CrossOrigin(exposedHeaders = "Location")
    @PostMapping("/")
    public ResponseEntity<Attachment> save(@RequestBody Attachment attachment, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException(errors.toString());
        }
        LOG.debug("Controller requesting a new attachment to be saved with id {}", attachment.getId());
        Attachment incomingAttachment = attachmentService.save(attachment);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/content/{id}")
                .buildAndExpand(incomingAttachment.getId()).toUri()).body(incomingAttachment);
    }

    @GetMapping("/content/{id}")
    public ResponseEntity<byte[]> getContent(@PathVariable UUID id) {
        byte[] attachmentContent = attachmentService.getContent(id);
        if (attachmentContent == null) {
            LOG.debug("Controller requesting attachment with ID {} that has no content", id);
            return ResponseEntity.notFound().build();
        }
        if (attachmentContent.length > 0) {
            return ResponseEntity.ok().body(attachmentContent);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/metadata/{attachmentId}")
    public ResponseEntity<?> getMetadata(@PathVariable UUID attachmentId) {
        LOG.debug("controller requesting metadata of attachment with ID {}", attachmentId);
        return ResponseEntity.ok(attachmentService.getMetadata(attachmentId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable UUID id) {
        Attachment attachment = attachmentService.getMetadata(id);
        byte[] attachmentContent = attachmentService.getContent(id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType
                (attachment.getType())).header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + attachment.getName()
                        + "\"").body(new ByteArrayResource(attachmentContent));
}

    @GetMapping("/")
    public ResponseEntity<?> getAllAttachmentsMetadata() {
        LOG.debug("Controller requesting to get all attachment metadata");
        return ResponseEntity.ok(attachmentService.getAll());
    }

    @PutMapping(value = "/content/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setContent(@PathVariable UUID id, @RequestBody byte[] content) {
        LOG.debug("Controller requesting to update content with ID {}", id);
        return ResponseEntity.accepted().body(attachmentService.update(content, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        LOG.debug("Controller requesting to delete attachment with ID {}", id);
        attachmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}