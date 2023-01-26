package com.globallogic.amcr.repository.attachmentcomponent;

import com.globallogic.amcr.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.model.attachmentcomponent.AttachmentMetadata;
import com.globallogic.amcr.model.attachmentcomponent.AttachmentResponse;

import java.util.List;
import java.util.UUID;

public interface AttachmentDao {

    byte[] getContent(UUID id);

    Attachment update(UUID id, byte[] content, Attachment oldAttachment);

    void delete(UUID id);

    Attachment save(Attachment attachment, UUID id);

    Attachment get(UUID id);

    List<AttachmentResponse> getAll();

    AttachmentMetadata getMetadata(UUID id);
}