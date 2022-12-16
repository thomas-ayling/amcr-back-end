package com.globallogic.amcr.controller.contactcomponent;

import com.globallogic.amcr.persistence.model.contactcomponent.Feedback;
import com.globallogic.amcr.persistence.payload.contactcomponent.FeedbackResponse;
import com.globallogic.amcr.service.contactcomponent.EmailService;
import com.globallogic.amcr.service.contactcomponent.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * endpoint for feedback upload and download
 */

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

    /**
     * @param feedback   the feedback json object that gets mapped to feedback type
     * @param attachment
     * @return returns a response entity either OK (200) or INTERNAL_SERVER_ERROR (500)
     */

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity uploadFeedback(@RequestPart("feedback") Feedback feedback, @RequestPart(value = "attachment", required = false) MultipartFile attachment) {
        return feedbackService.save(feedback, attachment);
    }

    /**
     * @return returns a list of all entries in the feedback table
     */

    @GetMapping("/get-all")
    public List<FeedbackResponse> getMany() {
        return feedbackService.getAll();
    }

    /**
     * @return returns a list of the 10 latest entries in the feedback table
     */

    @GetMapping("/get-latest")
    public List<FeedbackResponse> getLatest() {
        return feedbackService.getLatest();
    }

    /**
     * @param last the 'feedback order' of the last received feedback entry
     * @return returns the 10 entries that follow the 'last' entry
     */

    @GetMapping("/get-older/{last}")
    public List<FeedbackResponse> getLatest(@PathVariable int last) {
        return feedbackService.getOlder(last);
    }


}
