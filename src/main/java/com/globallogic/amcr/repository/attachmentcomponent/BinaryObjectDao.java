package com.globallogic.amcr.repository.attachmentcomponent;

import com.globallogic.amcr.model.attachmentcomponent.BinaryObject;
import com.globallogic.amcr.model.attachmentcomponent.Metadata;
import com.globallogic.amcr.model.attachmentcomponent.Response;

import java.util.UUID;

public interface BinaryObjectDao {

    BinaryObject get(UUID id);

    Metadata saveMetadata(Metadata metadata, UUID id, UUID mediaId);

    void updateMedia(byte[] media, UUID id);

    Response getMedia(UUID metadataId);

    void delete(UUID id);
}