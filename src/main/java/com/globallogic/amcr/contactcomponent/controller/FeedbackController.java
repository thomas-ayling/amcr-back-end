package com.globallogic.amcr.contactcomponent.controller;

import com.globallogic.amcr.contactcomponent.persistence.model.Feedback;
import com.globallogic.amcr.contactcomponent.persistence.payload.FeedbackResponse;
import com.globallogic.amcr.contactcomponent.service.EmailServiceImpl;
import com.globallogic.amcr.contactcomponent.service.FeedbackServiceImpl;
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

    private final EmailServiceImpl emailServiceImpl;
    private final FeedbackServiceImpl feedbackServiceImpl;

    public FeedbackController(EmailServiceImpl emailServiceImpl, FeedbackServiceImpl feedbackServiceImpl) {
        this.emailServiceImpl = emailServiceImpl;
        this.feedbackServiceImpl = feedbackServiceImpl;
    }

    /**
     * @param feedback   the feedback json object that gets mapped to feedback type
     * @param attachment
     * @return returns a response entity either OK (200) or INTERNAL_SERVER_ERROR (500)
     */

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity uploadFeedback(@RequestPart("feedback") Feedback feedback, @RequestPart(value = "attachment", required = false) MultipartFile attachment) {
        return feedbackServiceImpl.save(feedback, attachment);
    }

    /**
     * @return returns a list of all entries in the feedback table
     */

    @GetMapping("/get-all")
    public List<FeedbackResponse> getMany() {
        return feedbackServiceImpl.getAll();
    }

    /**
     * @return returns a list of the 10 latest entries in the feedback table
     */

    @GetMapping("/get-latest")
    public List<FeedbackResponse> getLatest() {
        return feedbackServiceImpl.getLatest();
    }

    /**
     * @param last the 'feedback order' of the last received feedback entry
     * @return returns the 10 entries that follow the 'last' entry
     */

    @GetMapping("/get-older/{last}")
    public List<FeedbackResponse> getLatest(@PathVariable int last) {
        return feedbackServiceImpl.getOlder(last);
    }


}
