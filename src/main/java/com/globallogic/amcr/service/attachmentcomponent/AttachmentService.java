package com.globallogic.amcr.service.attachmentcomponent;

import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentMetadata;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface AttachmentService {

    ResponseEntity create(MultipartFile attachment);

    ResponseEntity<Resource> get(UUID id);

    List<AttachmentMetadata> getAll();

    void delete(UUID id);
}
