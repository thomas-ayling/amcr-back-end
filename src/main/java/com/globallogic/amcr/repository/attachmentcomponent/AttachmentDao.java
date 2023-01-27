package com.globallogic.amcr.repository.attachmentcomponent;

import com.globallogic.amcr.model.attachmentcomponent.Attachment;

import java.util.List;
import java.util.UUID;

public interface AttachmentDao {

    Attachment getContent(UUID id);

    Attachment update(UUID id, byte[] content, Attachment oldAttachment);

    void delete(UUID id);

    Attachment save(Attachment attachment, UUID id);

    List<Attachment> getAll();

    Attachment getMetadata(UUID id);
}