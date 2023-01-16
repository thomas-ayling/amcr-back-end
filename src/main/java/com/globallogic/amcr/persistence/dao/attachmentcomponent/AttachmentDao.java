package com.globallogic.amcr.persistence.dao.attachmentcomponent;

import com.globallogic.amcr.persistence.dao.Dao;
import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentMetadata;

import java.util.List;
import java.util.UUID;

public interface AttachmentDao<T,R> extends Dao<T, R> {

    List<AttachmentMetadata> getAllMetadata();

    void delete(UUID id);
}
