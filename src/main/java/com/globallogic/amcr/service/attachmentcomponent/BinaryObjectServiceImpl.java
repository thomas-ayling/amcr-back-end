package com.globallogic.amcr.service.attachmentcomponent;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globallogic.amcr.model.attachmentcomponent.BinaryObject;
import com.globallogic.amcr.model.attachmentcomponent.Metadata;
import com.globallogic.amcr.model.attachmentcomponent.Response;
import com.globallogic.amcr.repository.attachmentcomponent.BinaryObjectDao;
import com.globallogic.amcr.utils.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Service
public class BinaryObjectServiceImpl implements BinaryObjectService {
    private final BinaryObjectDao binaryObjectDao;
    private final Logger LOG = LoggerFactory.getLogger(BinaryObjectServiceImpl.class);

    public BinaryObjectServiceImpl(BinaryObjectDao binaryObjectDao) {
        this.binaryObjectDao = Assert.assertNotNull(binaryObjectDao, "Dao cannot be null");
    }

    @Override
    @Transactional
    public Metadata save(Metadata metadata) {
        UUID id = UUID.randomUUID();
        UUID mediaId = UUID.randomUUID();
        LOG.debug("Service saving metadata with id {}:\n {}", id, metadata);
        return binaryObjectDao.saveMetadata(metadata, id, mediaId);
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

    @Override
    @Transactional
    public void delete(UUID id) {
        LOG.debug("Service deleting metadata with ID {}", id);
        binaryObjectDao.delete(id);
    }
}