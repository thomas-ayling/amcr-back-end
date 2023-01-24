package com.globallogic.amcr.service.impl.attachmentcomponent;

import com.globallogic.amcr.repository.impl.attachmentcomponent.AttachmentDaoImpl;
import com.globallogic.amcr.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.model.attachmentcomponent.AttachmentMetadata;
import com.globallogic.amcr.model.attachmentcomponent.AttachmentResponse;
import com.globallogic.amcr.service.attachmentcomponent.AttachmentService;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    public final Logger Log = LoggerFactory.getLogger(AttachmentServiceImpl.class);
    private final AttachmentDaoImpl attachmentDAOImpl;

    public AttachmentServiceImpl(AttachmentDaoImpl attachmentDAOImpl) {
        this.attachmentDAOImpl = Assert.assertNotNull(attachmentDAOImpl, "AttachmentDAO is not present");
    }

    @Transactional
    public Attachment save(Attachment attachment) {
        UUID id = UUID.randomUUID();
        Log.debug("Attempting to upload an attachment {}", attachment);
        return attachmentDAOImpl.save(attachment, id);

    }

    @Transactional(readOnly = true)
    public AttachmentResponse get(UUID id) {
        Assert.assertNotNull(id, "Attachment ID cannot be null");
        Log.debug("Attempting to retrieve an attachment with ID {}", id);
        return attachmentDAOImpl.get(id);
    }

    @Transactional(readOnly = true)
    public List<AttachmentMetadata> getAll() {
        Log.debug("Service attempting to retrieve all metadata for all attachments in the database");
        return attachmentDAOImpl.getAllMetadata();
    }

    @Transactional
    public void delete(UUID id) {
        Log.debug("Attempting to delete an attachment with a ID {}", id);
        attachmentDAOImpl.delete(id);
    }
}
