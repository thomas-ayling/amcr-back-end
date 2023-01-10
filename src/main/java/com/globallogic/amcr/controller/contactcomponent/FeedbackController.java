package com.globallogic.amcr.controller.contactcomponent;

import com.globallogic.amcr.exception.NotFoundException;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * endpoint for feedback upload and download
 */
@RestController
@RequestMapping("/feedback")
@CrossOrigin
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
    public ResponseEntity<?> uploadFeedback(@RequestPart("feedback") @Validated Feedback feedback, BindingResult errors, @RequestPart(value = "attachment", required = false) MultipartFile incomingAttachment) {
        if (errors.hasErrors()) {
            throw new NotFoundException(errors.toString());
        }
        try {
            // Create new Attachment object with params taken from MultipartFile
            Attachment attachment = incomingAttachment == null ? null : new Attachment(StringUtils.cleanPath(Objects.requireNonNull(incomingAttachment.getOriginalFilename())), incomingAttachment.getContentType(), incomingAttachment.getSize(), incomingAttachment.getBytes());
            Feedback returnedFeedback = feedbackService.save(feedback, attachment);
            return ResponseEntity.ok().body(returnedFeedback);
        } catch (IOException ioe) {
            throw new RuntimeException("Error in feedback controller - attachment could not be read", ioe);
        }

    }

    /**
     * Usage is as follows:
     * Calling url/feedback returns all feedback
     * Calling url/feedback?latest=true returns the latest feedback
     * Calling url/feedback?older=true&last=number returns the 10 entries older than the specified number
     *
     * @param latest option for returning the 10 latest feedback entries
     * @param older  option for returning older feedback specified by the 'last' parameter. 'last' must be included if this option is true
     * @param last   must be included if 'older' is true, the server returns the 10 last entries older than the specified entry
     * @return returns a list of all entries in the feedback table
     */
    @GetMapping()
    public List<FeedbackResponse> get(@RequestParam(required = false) Boolean latest, @RequestParam(required = false) Boolean older, @RequestParam(required = false) Integer last) {
        latest = latest != null && latest;
        older = older != null && older;
        if (!older && last != null) {
            throw new RuntimeException("'Last' is not necessary if 'older' is not true");
        }
        if (older && last == null) {
            throw new RuntimeException("'Last' param must be present if 'older' is true.");
        }
        if (latest && older) {
            throw new RuntimeException("'Latest' and 'older' cannot both be true. Select one or the other.");
        }
        if (latest) {
            return feedbackService.getLatest();
        }
        if (older) {
            return feedbackService.getOlder(last);
        }
        return feedbackService.getAll();
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
