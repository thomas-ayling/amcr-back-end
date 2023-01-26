package com.globallogic.amcr.service.attachmentcomponent;

import com.globallogic.amcr.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.model.attachmentcomponent.AttachmentMetadata;
import com.globallogic.amcr.model.attachmentcomponent.AttachmentResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globallogic.amcr.repository.attachmentcomponent.AttachmentDao;
import com.globallogic.amcr.utils.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentDao attachmentDao;
    private final Logger LOG = LoggerFactory.getLogger(AttachmentServiceImpl.class);

    public AttachmentServiceImpl(AttachmentDao attachmentDao) {
        this.attachmentDao = Assert.assertNotNull(attachmentDao, "Dao cannot be null");
    }

    @Override
    @Transactional
    public Attachment save(Attachment attachment) {
        UUID id = UUID.randomUUID();
        LOG.debug("Service saving attachment with id {}:\n {}", id, attachment);
        return attachmentDao.save(attachment, id);
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] getBinary(UUID id) {
        LOG.debug("Service saving new attachment binary");
        return attachmentDao.getContent(id);
    }

    @Override
    @Transactional
    public Attachment update(byte[] content, UUID id) {
        Attachment oldAttachment = attachmentDao.get(id);
        LOG.debug("Service updating new attachment");
        return attachmentDao.update(id, content, oldAttachment);
    }

    @Override
    @Transactional(readOnly = true)
    public Attachment get (UUID id) {
        LOG.debug("Service requesting attachment with ID {}", id);
        return attachmentDao.get(id);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        LOG.debug("Service deleting attachment with ID {}", id);
        attachmentDao.delete(id);
    }

    @Override
    @Transactional
    public List<AttachmentResponse> getAll() {
        LOG.debug("Service requesting all attachments");
        return attachmentDao.getAll();
    }

    @Override
    @Transactional
    public AttachmentMetadata getMetadata(UUID id) {
        LOG.debug("Service requesting attachment metadata with ID {}", id);
        return attachmentDao.getMetadata(id);
    }
}