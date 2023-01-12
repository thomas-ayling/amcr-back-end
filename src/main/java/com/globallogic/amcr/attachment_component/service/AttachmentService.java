package com.globallogic.amcr.attachment_component.service;

import com.globallogic.amcr.attachment_component.persistance.model.Attachment;
import com.globallogic.amcr.attachment_component.persistance.payload.AttachmentMetadata;
import com.globallogic.amcr.attachment_component.persistance.payload.AttachmentResponse;
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
