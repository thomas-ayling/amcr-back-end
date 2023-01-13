package com.globallogic.amcr.service.attachmentcomponent;

import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentMetadata;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface AttachmentService {

    ResponseEntity upload(MultipartFile attachment);

    ResponseEntity<Resource> retrieve(UUID id);

    List<AttachmentMetadata> retrieveAll();

    void delete(UUID id);
}
