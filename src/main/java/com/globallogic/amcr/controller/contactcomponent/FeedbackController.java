package com.globallogic.amcr.controller.contactcomponent;

import com.globallogic.amcr.persistence.model.contactcomponent.Feedback;
import com.globallogic.amcr.persistence.payload.contactcomponent.FeedbackResponse;
import com.globallogic.amcr.service.contactcomponent.EmailService;
import com.globallogic.amcr.service.contactcomponent.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public ResponseEntity uploadFeedback( @RequestPart("feedback") Feedback feedback, @RequestPart(value = "attachment", required = false) MultipartFile attachment) {
        return feedbackService.save(feedback, attachment);
    }

    @GetMapping("/get-all")
    public List<FeedbackResponse> getMany() {
        return feedbackService.getAll();
    }

    @GetMapping("/get-latest")
    public List<FeedbackResponse> getLatestFeedback() {
        return feedbackService.getLatest();
    }

    @GetMapping("/get-older/{last}")
    public List<FeedbackResponse> getLatestFeedback(@PathVariable int last) {
        return feedbackService.getOlder(last);
    }


}
