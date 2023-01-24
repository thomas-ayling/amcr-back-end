package com.globallogic.amcr.persistence.dao.attachmentcomponent;

import com.globallogic.amcr.mapper.attachmentcomponent.AttachmentMapper;
import com.globallogic.amcr.persistence.dao.Dao;
import com.globallogic.amcr.persistence.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentMetadata;
import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentResponse;
import com.globallogic.amcr.utils.Assert;
import com.globallogic.amcr.utils.ByteConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@Repository
public class AttachmentDaoImpl implements Dao<Attachment, AttachmentResponse> {
    public final Logger Log = LoggerFactory.getLogger(AttachmentDaoImpl.class);
    private final AttachmentMapper attachmentMapper;

    public AttachmentDaoImpl(AttachmentMapper attachmentMapper) {
        this.attachmentMapper = Assert.assertNull(attachmentMapper, "Attachment mapper cannot be null");
    }

    @Override
    public Attachment save(Attachment attachmentFile, UUID id) {
        try {
            attachmentFile.setId(id);
            attachmentFile.setDownloadUri(ServletUriComponentsBuilder.fromCurrentContextPath().path("/attachments/retrieve/").path(id.toString()).toUriString());
            attachmentMapper.save(attachmentFile);
            return attachmentFile;
        } catch (Exception e) {
            throw new RuntimeException(attachmentFile.getName() + " could not be saved.", e);
        }
    }

    @Override
    public AttachmentResponse get(UUID id) {
        Log.trace("Requests an attachment with ID{}", id);
        AttachmentResponse attachment = attachmentMapper.get(id);
        attachment.setReadableSize(ByteConverter.bytesToReadable(attachment.getSize()));
        return attachmentMapper.get(id);
    }

    @Override
    public List<AttachmentResponse> getAll() {
        Log.trace("DAO requests all metadata available for every attachment");
        return attachmentMapper.getAll();
    }

    public List<AttachmentMetadata> getAllMetadata() {
        Log.trace("DAO requests all metadata available for every attachment");
        return attachmentMapper.getAllMetadata();
    }

    public void delete(UUID id) {
        try {
            Log.trace("Attempts to delete an attachment with ID{}", id);
            attachmentMapper.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("DAO could not delete attachment with ID " + id, e);
        }
    }
}
