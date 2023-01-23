package com.globallogic.amcr.repository.attachmentcomponent;

import com.globallogic.amcr.model.attachmentcomponent.Attachment;

import java.util.UUID;

public interface AttachmentDao {

    byte[] getBinary(UUID id);

    Attachment update(UUID id, byte[] content, Attachment oldAttachment);

    void delete(UUID id);

    Attachment save(Attachment attachment, UUID id);

    Attachment get(UUID id);
}