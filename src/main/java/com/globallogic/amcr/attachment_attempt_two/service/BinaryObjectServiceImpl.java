package com.globallogic.amcr.attachment_attempt_two.service;

import com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two.BinaryObject;
import com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two.Metadata;
import com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two.Response;
import com.globallogic.amcr.attachment_attempt_two.repository.attachment_attempt_two.BinaryObjectDao;
import com.globallogic.amcr.utils.Assert;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class BinaryObjectServiceImpl implements BinaryObjectService {
    private final BinaryObjectDao binaryObjectDao;
    private final Logger LOG = LoggerFactory.getLogger(BinaryObjectServiceImpl.class);

    public BinaryObjectServiceImpl(BinaryObjectDao binaryObjectDao) {
        this.binaryObjectDao = Assert.assertNull(binaryObjectDao, "Dao cannot be null");
    }

    @Override
    @Transactional
    public Metadata save(Metadata metadata) {
        UUID id = UUID.randomUUID();
        UUID mediaId = UUID.randomUUID();

        LOG.debug("Service saving metadata with id {}:\n {}", id, metadata);

        Metadata returnedMetadata = binaryObjectDao.saveMetadata(metadata, id, mediaId);
        return returnedMetadata;
    }

    @Override
    @Transactional(readOnly = true)
    public BinaryObject get(UUID id) {
        LOG.debug("Service saving new BinaryObject");
        return binaryObjectDao.get(id);
    }

    @Override
    @Transactional
    public void updateMedia (byte[] media, UUID id) {
        LOG.debug("Service saving new BinaryObject");
        binaryObjectDao.updateMedia(media, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Response getMedia (UUID metadataId) {
        LOG.debug("Service requesting media with ID {}", metadataId);
        return binaryObjectDao.getMedia(metadataId);
    }
}
