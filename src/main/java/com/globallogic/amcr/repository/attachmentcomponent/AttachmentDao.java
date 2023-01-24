package com.globallogic.amcr.repository.attachmentcomponent;

import com.globallogic.amcr.repository.Dao;
import com.globallogic.amcr.model.attachmentcomponent.AttachmentMetadata;

import java.util.List;
import java.util.UUID;

public interface AttachmentDao<T,R> extends Dao<T, R> {

    List<AttachmentMetadata> getAllMetadata();

    void delete(UUID id);
}
