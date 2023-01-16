package com.globallogic.amcr.controller.attachmentcomponent;

import com.globallogic.amcr.persistence.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentMetadata;
import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentResponse;
import com.globallogic.amcr.service.attachmentcomponent.AttachmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.zip.CRC32C;
import java.util.zip.Checksum;

@RestController
@RequestMapping("/attachments")
@CrossOrigin
public class AttachmentController {
    public final Logger Log = LoggerFactory.getLogger(AttachmentController.class);
    private final AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Attachment> uploadAttachment(@RequestPart(value = "attachment") MultipartFile incomingAttachment) {
        String attachmentName = StringUtils.cleanPath(Objects.requireNonNull(incomingAttachment.getOriginalFilename()));
        try {
            byte[] data1 = incomingAttachment.getBytes();
            Checksum crc32c = new CRC32C();
            crc32c.update(data1);
            long crc = crc32c.getValue();
            File file = new File(attachmentName);
            String mimeType = Files.probeContentType(file.toPath());

            if (mimeType != null && mimeType.split("/")[0].equalsIgnoreCase("image")) {
                try {
                    BufferedImage image = ImageIO.read(incomingAttachment.getInputStream());

                    Map<String, Object> metadata = new HashMap<>();
                    metadata.put("heightInPixels", image.getWidth());
                    metadata.put("widthInPixels", image.getHeight());

                    Attachment attachment = new Attachment(attachmentName, incomingAttachment.getContentType(), incomingAttachment.getSize(), crc, metadata, incomingAttachment.getBytes());
                    attachmentService.save(attachment);
                    return ResponseEntity.ok().build();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Attachment attachment = new Attachment(attachmentName, incomingAttachment.getContentType(), incomingAttachment.getSize(), crc, incomingAttachment.getBytes());

            Log.trace("Attempts to upload a new attachment {}", attachment);
            Log.debug("Saving a new attachment {}", attachment);
            attachmentService.save(attachment);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> retrieveAttachment(@PathVariable UUID id) {
        Log.debug("Requesting an attachment with ID {}", id);
        AttachmentResponse attachmentResponse = attachmentService.get(id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachmentResponse
                        .getContentType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; name=\""
                        + attachmentResponse.getName() + "\"")
                .body(new ByteArrayResource(attachmentResponse
                        .getData()));
    }

    @GetMapping("/")
    public ResponseEntity<List<AttachmentMetadata>> retrieveAllAttachments() {
        Log.debug("Requesting all attachments");
        return ResponseEntity.ok().body(attachmentService.getAll());
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteAttachment(@PathVariable UUID id) {
        try {
            Log.debug("Requesting to delete attachment with ID {}", id);
            attachmentService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Controller could not delete attachment", e);
        }
    }
}