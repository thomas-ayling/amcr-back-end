package com.globallogic.amcr.attachment_component.persistance.dao;

import com.globallogic.amcr.attachment_component.persistance.payload.AttachmentMetadata;

import java.util.List;
import java.util.UUID;

public interface DAO<T, R> {
    void upload(T t);
    R retrieve(UUID id);
    List<AttachmentMetadata> retrieveAll();
}
