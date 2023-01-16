package com.globallogic.amcr.persistence.dao;

import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentMetadata;

import java.util.List;
import java.util.UUID;

public interface AttachmentDao<T,R> {

    List<AttachmentMetadata> getAllMetadata();

    void delete(UUID id);
}
