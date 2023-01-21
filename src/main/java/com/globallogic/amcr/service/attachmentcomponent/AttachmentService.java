package com.globallogic.amcr.service.attachmentcomponent;

import com.globallogic.amcr.persistence.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentMetadata;
import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentResponse;

import java.util.List;
import java.util.UUID;

public interface AttachmentService {

    Attachment save(Attachment attachment);

    AttachmentResponse get(UUID id);

    List<AttachmentMetadata> getAll();

    void delete(UUID id);
}