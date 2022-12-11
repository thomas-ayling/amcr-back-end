package com.globallogic.amcr.controller;

import com.globallogic.amcr.model.Feedback;
import com.globallogic.amcr.payload.AttachmentMetadata;
import com.globallogic.amcr.payload.FeedbackResponse;
import com.globallogic.amcr.service.EmailService;
import com.globallogic.amcr.service.FeedbackService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/feedback")
@CrossOrigin(origins = "*")
public class FeedbackController {

    private final EmailService emailService;
    private final FeedbackService feedbackService;

    public FeedbackController(EmailService emailService, FeedbackService feedbackService) {
        this.emailService = emailService;
        this.feedbackService = feedbackService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity uploadFeedback(
            @RequestPart("feedback") Feedback feedback,
            @RequestPart(value = "attachment", required = false) MultipartFile attachment
    ) {
        UUID feedbackId = feedbackService.save(feedback, attachment);
        AttachmentMetadata attachmentMetadata = feedbackService.getAttachmentMetadata(feedbackId);
        return emailService.sendMail(feedback, attachmentMetadata, 0);
    }

    @GetMapping("/get-many")
    public List<FeedbackResponse> getManyFeedback() {
        return feedbackService.getAll();
    }

    @GetMapping("/get-latest")
    public List<FeedbackResponse> getLatestFeedback() {
        return feedbackService.getLatest();
    }

    @GetMapping("/get-older/{last}")
    public List<FeedbackResponse> getLatestFeedback(@PathVariable Integer last) {
        return feedbackService.getOlder(last);
    }

    @GetMapping("/file-download/{id}")
    public ResponseEntity<Resource> getAttachment(@PathVariable UUID id) {
        return feedbackService.getAttachment(id);
    }

}
