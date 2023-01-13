package com.globallogic.amcr.persistence.dao.attachmentcomponent;

import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentMetadata;

import java.util.List;
import java.util.UUID;

public interface DAO<T, R> {
    void upload(T t);
    R retrieve(UUID id);
    List<AttachmentMetadata> retrieveAll();
}
