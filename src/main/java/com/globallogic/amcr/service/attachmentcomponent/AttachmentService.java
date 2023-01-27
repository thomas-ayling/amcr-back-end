package com.globallogic.amcr.service.attachmentcomponent;

import com.globallogic.amcr.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.model.attachmentcomponent.Content;

import java.util.List;
import java.util.UUID;

public interface AttachmentService {

    Attachment save(Attachment attachment);

    Content getContent(UUID id);

    Attachment update(byte[] content, UUID id);

    void delete(UUID id);

    List<Attachment> getAll();

    Attachment getMetadata(UUID id);
}
