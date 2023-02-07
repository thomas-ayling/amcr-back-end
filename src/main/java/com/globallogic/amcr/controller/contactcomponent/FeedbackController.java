package com.globallogic.amcr.controller.contactcomponent;

import com.globallogic.amcr.controller.casestudies.CaseStudyController;
import com.globallogic.amcr.exception.NotFoundException;
import com.globallogic.amcr.model.contactcomponent.Feedback;
import com.globallogic.amcr.model.contactcomponent.FeedbackAttachment;
import com.globallogic.amcr.model.contactcomponent.FeedbackAttachmentResponse;
import com.globallogic.amcr.service.contactcomponent.FeedbackService;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Endpoint for feedback upload and download
 */
@RestController
@RequestMapping("/feedback")
@CrossOrigin
public class FeedbackController {
    private final Logger Log = LoggerFactory.getLogger(CaseStudyController.class);
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = Assert.assertNotNull(feedbackService, "Feedback service cannot be null");
    }

    /**
     * @param feedback           The feedback json object that gets mapped to feedback type
     * @param incomingAttachment The optional attachment from the client
     * @return Returns a response entity either OK (200) or INTERNAL_SERVER_ERROR (500)
     */
    @PostMapping(value = "/", consumes = {"multipart/form-data"}, produces = {"application/json"})
    public ResponseEntity<Feedback> uploadFeedback(@RequestPart("feedback") @Validated Feedback feedback, BindingResult errors, @RequestPart(value = "attachment", required = false) MultipartFile incomingAttachment) {
        if (errors.hasErrors()) {
            throw new NotFoundException(errors.toString());
        }
        try {
            // Create new FeedbackAttachment object with params taken from MultipartFile
            FeedbackAttachment feedbackAttachment = incomingAttachment == null ? null : new FeedbackAttachment(StringUtils.cleanPath(Objects.requireNonNull(incomingAttachment.getOriginalFilename())), incomingAttachment.getContentType(), incomingAttachment.getSize(), incomingAttachment.getBytes());
            Log.debug("Controller saving new feedback");
            Feedback createdFeedback = feedbackService.save(feedback, feedbackAttachment);
            Log.debug("Controller has saved new feedback");
            return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdFeedback.getId()).toUri()).body(createdFeedback);
        } catch (IOException ioe) {
            throw new RuntimeException("Error in feedback controller - attachment could not be read", ioe);
        }

    }

    @GetMapping(value = ("/{id}"), produces = "application/json")
    public ResponseEntity<Feedback> get(@PathVariable UUID id) {
        Log.debug("Controller requesting feedback with ID {}", id);
        return ResponseEntity.ok().body(feedbackService.get(id));
    }

    /**
     * Usage is as follows:
     * <ul>
     * <li>Calling url/feedback returns all feedback</li>
     * <li>Calling url/feedback?latest=true returns the latest feedback</li>
     * <li>Calling url/feedback?older=true&last={int} returns the 10 entries older than the specified number</li>
     * </ul>
     *
     * @param latest option for returning the 10 latest feedback entries
     * @param older  option for returning older feedback specified by the 'last' parameter. 'last' must be included if this option is true
     * @param last   must be included if 'older' is true, the server returns the 10 last entries older than the specified entry
     * @return returns a list of all entries in the feedback table
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Feedback>> getWithParams(@RequestParam(required = false) Boolean latest, @RequestParam(required = false) Boolean older, @RequestParam(required = false) Integer last) {
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
            Log.debug("Controller requesting latest feedback entries");
            return ResponseEntity.ok().body(feedbackService.getLatest());
        }
        if (older) {
            Log.debug("Controller requesting older feedback entries");
            return ResponseEntity.ok().body(feedbackService.getOlder(last));
        }
        Log.debug("Controller requesting all feedback");
        return ResponseEntity.ok().body(feedbackService.getAll());
    }

    /**
     * @param attachmentId the id of the attachment to be downloaded
     * @return returns a response entity with the relevant headers and the binary data to allow for easy download on the front end
     */
    @GetMapping(value = "/attachment/{attachmentId}")
    public ResponseEntity<Resource> getAttachment(@PathVariable UUID attachmentId) {
        Log.debug("Controller requesting attachment with ID {}", attachmentId);
        FeedbackAttachmentResponse feedbackAttachmentResponse = feedbackService.getAttachment(attachmentId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(feedbackAttachmentResponse.getAttachmentType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + feedbackAttachmentResponse.getAttachmentName() + "\"").body(new ByteArrayResource(feedbackAttachmentResponse.getData()));
    }

    /**
     * @return returns the number of entries in the feedback table
     */
    @GetMapping("/count")
    public ResponseEntity<Integer> getCount() {
        Log.debug("Controller requesting total number of entries");
        return ResponseEntity.ok().body(feedbackService.getCount());
    }
}