package com.globallogic.amcr.repository.impl.attachmentcomponent;

import com.globallogic.amcr.model.attachmentcomponent.Attachment;
import org.springframework.stereotype.Repository;

import com.globallogic.amcr.repository.attachmentcomponent.AttachmentDao;
import com.globallogic.amcr.utils.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
        attachment.setDownloadUri(ServletUriComponentsBuilder.fromCurrentContextPath().path("/attachment/").path(id.toString()).toUriString());
        LOG.trace("DAO saving attachment {}", attachment);
        attachmentMapper.saveMetadata(attachment);
        return attachment;
    }

    @Override
    public Attachment update(UUID id, Attachment newAttachment, Attachment oldAttachment) {
        LOG.trace("DAO updating attachment with ID {}", id);
        attachmentMapper.updateMedia(newAttachment, id);
        newAttachment.setAllButContent(oldAttachment);
        return newAttachment;
    }

    @Override
    public byte[] getBinary(UUID id) {
        LOG.trace("DAO requesting attachment {}", id);
        return attachmentMapper.getBinary(id);
    }

    @Override
    public Attachment get(UUID id) {
        LOG.trace("Dao requesting attachment with ID {}", id);
        return attachmentMapper.get(id);
    }

    // Not needed
    @Override
    public List<Attachment> getAll() {
        return null;
    }

    @Override
    public void delete(UUID id) {
        LOG.trace("DAO requesting to delete attachment with ID {}", id);
        attachmentMapper.delete(id);
    }
}