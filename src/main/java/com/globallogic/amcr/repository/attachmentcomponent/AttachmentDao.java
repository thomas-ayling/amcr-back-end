package com.globallogic.amcr.repository.attachmentcomponent;

import com.globallogic.amcr.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.repository.CrudDao;

import java.util.UUID;

public interface AttachmentDao extends CrudDao<Attachment, Attachment> {

    /**
     *
     * @param id
     * @return the pure binary of the attachment
     */
    byte[] getBinary(UUID id);
}