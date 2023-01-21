package com.globallogic.amcr.repository.impl.attachmentcomponent;

import org.springframework.stereotype.Repository;

import com.globallogic.amcr.model.attachmentcomponent.BinaryObject;
import com.globallogic.amcr.model.attachmentcomponent.Metadata;
import com.globallogic.amcr.model.attachmentcomponent.Response;
import com.globallogic.amcr.repository.attachmentcomponent.BinaryObjectDao;
import com.globallogic.amcr.utils.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@Repository
public class BinaryObjectDaoImpl implements BinaryObjectDao {
    private final Logger LOG = LoggerFactory.getLogger(BinaryObject.class);
    private final BinaryObjectMapper binaryObjectMapper;

    public BinaryObjectDaoImpl(BinaryObjectMapper binaryObjectMapper) {
        this.binaryObjectMapper = Assert.assertNotNull(binaryObjectMapper, "BinaryObject mapper cannot be null");
    }

    @Override
    public Metadata saveMetadata(Metadata metadata, UUID id, UUID mediaId) {
        metadata.setId(id);
        metadata.setMediaId(mediaId);
        metadata.setDownloadUri(ServletUriComponentsBuilder.fromCurrentContextPath().path("/binary/media/").path(id.toString()).toUriString());
        LOG.trace("DAO saving Metadata {}", metadata);
        binaryObjectMapper.saveMedia(metadata.getMediaId());
        binaryObjectMapper.saveMetadata(metadata);
        return metadata;
    }

    @Override
    public void updateMedia(byte[] media, UUID id) {
        LOG.trace("DAO saving media with {}", id);
        binaryObjectMapper.updateMedia(media, id);
    }

    @Override
    public BinaryObject get(UUID id) {
        LOG.trace("DAO requesting BinaryObject {}", id);
        return binaryObjectMapper.get(id);
    }

    @Override
    public Response getMedia(UUID metadataId) {
        LOG.trace("Dao requesting media with ID {}", metadataId);
        return binaryObjectMapper.getMedia(metadataId);
    }

    @Override
    public void delete(UUID id) {
        LOG.trace("DAO requesting to delete metadata with ID {}", id);
        binaryObjectMapper.delete(id);
    }
}