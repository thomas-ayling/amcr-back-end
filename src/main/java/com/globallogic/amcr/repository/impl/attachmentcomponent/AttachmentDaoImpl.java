package com.globallogic.amcr.repository.impl.attachmentcomponent;

import com.globallogic.amcr.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.repository.attachmentcomponent.AttachmentDao;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AttachmentDaoImpl implements AttachmentDao {
    private final Logger LOG = LoggerFactory.getLogger(AttachmentDaoImpl.class);
    private final AttachmentMapper attachmentMapper;

    public AttachmentDaoImpl(AttachmentMapper attachmentMapper) {
        this.attachmentMapper = Assert.assertNotNull(attachmentMapper, "Attachment mapper cannot be null");
    }

    @Override
    public Attachment save(Attachment attachment, UUID id) {
        attachment.setId(id);
        LOG.trace("DAO saving attachment {}", attachment);
        attachmentMapper.save(attachment);
        return attachment;
    }

    @Override
    public Attachment update(UUID id, byte[] content, Attachment oldAttachment) {
        LOG.trace("DAO updating attachment with ID {}", id);
        attachmentMapper.update(content, id);
        return Attachment.from(oldAttachment, content);
    }

    @Override
    public byte[] getContent(UUID id) {
        LOG.trace("DAO requesting attachment {}", id);
        return attachmentMapper.getContent(id) != null ? attachmentMapper.getContent(id).get("content") : null;
    }

    @Override
    public void delete(UUID id) {
        LOG.trace("DAO requesting to delete attachment with ID {}", id);
        attachmentMapper.delete(id);
    }

    @Override
    public List<Attachment> getAll() {
        LOG.trace("DAO requesting all attachments");
        return attachmentMapper.getAll();
    }

    @Override
    public Attachment getMetadata(UUID id) {
        LOG.trace("Dao requesting attachment metadata with ID {}", id);
        return attachmentMapper.getMetadata(id);
    }
}