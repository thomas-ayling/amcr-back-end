package com.globallogic.amcr.service.attachmentcomponent;

import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentMetadata;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface AttachmentService {
    /**
     * Saves an attachment to the database.
     *
     * @param attachment - takes an attachment as parameter.
     * @return an attachment if the request has been successful.
     */
    ResponseEntity upload(MultipartFile attachment);

    /**
     * Requests a particular attachment with a given ID.
     *
     * @param id - takes an ID of an attachment for a parameter.
     * @return the given attachment if the request has been successful.
     */
    ResponseEntity<Resource> retrieve(UUID id);

    /**
     * Requests all attachment metadata in the database.
     *
     * @return - the metadata for all attachments in the database.
     */
    List<AttachmentMetadata> retrieveAll();

    /**
     * Deleted a particular attachment with a given ID.
     *
     * @param id - takes an ID of an attachment for a parameter and deletes the attachment if the attachment exists.
     */
    void delete(UUID id);
}
