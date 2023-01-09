package com.globallogic.amcr.service.contactcomponent;

import com.globallogic.amcr.persistence.model.contactcomponent.Feedback;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface EmailService {
    /**
     * @param feedback   the feedback object containing data to be sent in the email
     * @param feedbackId the id of the feedback to be used for requesting the metadata of any attached files
     * @return Response Entity with the status of the response
     */
    void sendMail(Feedback feedback, UUID feedbackId);
}
