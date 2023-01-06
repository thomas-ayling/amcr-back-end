package com.globallogic.amcr.controller.contactcomponent;

import com.globallogic.amcr.persistence.model.contactcomponent.Attachment;
import com.globallogic.amcr.persistence.model.contactcomponent.Feedback;
import com.globallogic.amcr.persistence.payload.contactcomponent.AttachmentResponse;
import com.globallogic.amcr.persistence.payload.contactcomponent.FeedbackResponse;
import com.globallogic.amcr.service.contactcomponent.FeedbackService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * endpoint for feedback upload and download
 */
@RestController
@RequestMapping("/feedback")
@CrossOrigin(origins = "*")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    /**
     * @param feedback           the feedback json object that gets mapped to feedback type
     * @param incomingAttachment the optional attachment from the client
     * @return returns a response entity either OK (200) or INTERNAL_SERVER_ERROR (500)
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity uploadFeedback(@RequestPart("feedback") Feedback feedback, @RequestPart(value = "attachment", required = false) MultipartFile incomingAttachment) {
        try {
            // Create new Attachment object with params taken from MultipartFile
            Attachment attachment = incomingAttachment == null ? null : new Attachment(StringUtils.cleanPath(Objects.requireNonNull(incomingAttachment.getOriginalFilename())), incomingAttachment.getContentType(), incomingAttachment.getSize(), incomingAttachment.getBytes());
            Feedback returnedFeedback = feedbackService.save(feedback, attachment);
            return ResponseEntity.ok().body(returnedFeedback);

        } catch (Exception e) {
            throw new RuntimeException("Error uploading feedback", e);
        }
    }

    /**
     * @return returns a list of all entries in the feedback table
     */
    @GetMapping("/all")
    public List<FeedbackResponse> getMany() {
        return feedbackService.getAll();
    }

    /**
     * @return returns a list of the 10 latest entries in the feedback table
     */
    @GetMapping("/latest")
    public List<FeedbackResponse> getLatest() {
        return feedbackService.getLatest();
    }

    /**
     * @param last the 'feedback order' of the last received feedback entry
     * @return returns the 10 entries that follow the 'last' entry
     */
    @GetMapping("/older/{last}")
    public List<FeedbackResponse> getLatest(@PathVariable int last) {
        return feedbackService.getOlder(last);
    }

    /**
     * @param fileId the id of the file to be downloaded
     * @return returns a response entity with the relevant headers and the binary data to allow for easy download on the front end
     */
    @GetMapping("/file/{fileId}")
    public ResponseEntity<Resource> getAttachment(@PathVariable UUID fileId) {
        try {
            AttachmentResponse attachmentResponse = feedbackService.getFile(fileId);
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachmentResponse.getFileType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachmentResponse.getFileName() + "\"").body(new ByteArrayResource(attachmentResponse.getData()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
