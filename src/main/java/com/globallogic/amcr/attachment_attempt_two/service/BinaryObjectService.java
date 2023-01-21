package com.globallogic.amcr.attachment_attempt_two.service;

import com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two.BinaryObject;
import com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two.Metadata;
import com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two.Response;

import java.util.UUID;

public interface BinaryObjectService {

    Metadata save(Metadata metadata);

    BinaryObject get(UUID id);

    void updateMedia(byte[] media, UUID id);

    Response getMedia(UUID metadataId);

    void delete(UUID id);
}
