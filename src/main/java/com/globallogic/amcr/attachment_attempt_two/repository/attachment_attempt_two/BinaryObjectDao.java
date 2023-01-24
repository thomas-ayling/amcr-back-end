package com.globallogic.amcr.attachment_attempt_two.repository.attachment_attempt_two;

import com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two.BinaryObject;
import com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two.Metadata;
import com.globallogic.amcr.attachment_attempt_two.model.attachment_attempt_two.Response;

import java.util.UUID;

public interface BinaryObjectDao {

    BinaryObject get(UUID id);

    Metadata saveMetadata(Metadata metadata, UUID id, UUID mediaId);

    void updateMedia(byte[] media, UUID id);

    Response getMedia(UUID metadataId);

    void delete(UUID id);
}
