package com.globallogic.amcr.service.contactcomponent;


import com.globallogic.amcr.persistence.payload.contactcomponent.AttachmentMetadata;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileService {
    /**
     * @param attachment the attachment to be saved
     * @param feedbackId the id of the feedback that the attachment belongs to
     * @return a response entity with the status of the transaction
     */
    ResponseEntity save(MultipartFile attachment, UUID feedbackId);

    /**
     * @param id the id of the requested attachment
     * @return a response entity with the file data embedded
     */
    ResponseEntity<Resource> get(UUID id);

    /**
     * @param feedbackId the id of the feedback that the file belongs to
     * @return an attachment metadata object with all information needed to generate a link to a file
     */
    AttachmentMetadata getAttachmentMetadata(UUID feedbackId);
}
