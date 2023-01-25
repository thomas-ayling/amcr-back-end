package com.globallogic.amcr.service.attachmentcomponent;

import com.globallogic.amcr.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.model.attachmentcomponent.AttachmentMetadata;
import com.globallogic.amcr.model.attachmentcomponent.AttachmentResponse;

import java.util.List;
import java.util.UUID;

public interface AttachmentService {

    Attachment save(Attachment attachment);

    byte[] getBinary(UUID id);

    Attachment update(byte[] content, UUID id);

    Attachment get(UUID id);

    void delete(UUID id);

    List<AttachmentResponse> getAll();

    AttachmentMetadata getMetadata(UUID id);
}