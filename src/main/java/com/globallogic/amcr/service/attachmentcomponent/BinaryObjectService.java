package com.globallogic.amcr.service.attachmentcomponent;

import com.globallogic.amcr.model.attachmentcomponent.BinaryObject;
import com.globallogic.amcr.model.attachmentcomponent.Metadata;
import com.globallogic.amcr.model.attachmentcomponent.Response;

import java.util.UUID;

public interface BinaryObjectService {

    Metadata save(Metadata metadata);

    BinaryObject get(UUID id);

    void updateMedia(byte[] media, UUID id);

    Response getMedia(UUID metadataId);

    void delete(UUID id);
}
