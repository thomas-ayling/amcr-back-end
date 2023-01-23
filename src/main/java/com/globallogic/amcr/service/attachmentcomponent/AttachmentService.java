package com.globallogic.amcr.service.attachmentcomponent;

import com.globallogic.amcr.model.attachmentcomponent.Attachment;

import java.util.UUID;

public interface AttachmentService {

    Attachment save(Attachment attachment);

    byte[] getBinary(UUID id);

    Attachment update(Attachment attachment, UUID id);

    Attachment get(UUID id);

    void delete(UUID id);
}
