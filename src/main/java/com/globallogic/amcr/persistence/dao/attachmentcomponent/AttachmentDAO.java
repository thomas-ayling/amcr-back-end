package com.globallogic.amcr.persistence.dao.attachmentcomponent;

import com.globallogic.amcr.mapper.attachmentcomponent.AttachmentMapper;
import com.globallogic.amcr.persistence.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentMetadata;
import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentResponse;
import com.globallogic.amcr.utils.Assert;
import com.globallogic.amcr.utils.ByteConverter;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.Imaging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.nio.file.Files;
import java.util.*;
import java.util.zip.CRC32C;
import java.util.zip.Checksum;

@Repository
public class AttachmentDAO implements DAO<MultipartFile, AttachmentResponse> {
    public final Logger Log = LoggerFactory.getLogger(AttachmentDAO.class);
    private final AttachmentMapper attachmentMapper;

    public AttachmentDAO(AttachmentMapper attachmentMapper) {
        this.attachmentMapper = Assert.assertNull(attachmentMapper, "Attachment mapper cannot be null");
    }

    public void save(MultipartFile attachment) {
        try {

            String attachmentName = StringUtils.cleanPath(Objects.requireNonNull(attachment.getOriginalFilename()));

            UUID id = UUID.randomUUID();

            byte[] data1 = attachment.getBytes();
            Checksum crc32c = new CRC32C();
            crc32c.update(data1);
            long crc = crc32c.getValue();

            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/attachments/retrieve/")
                    .path(id.toString())
                    .toUriString();

            File file = new File(attachmentName);
            String mimeType = Files.probeContentType(file.toPath());

            Attachment attachment1 = new Attachment(id, attachmentName, downloadUri, attachment.getContentType(),
                    ByteConverter.bytesToReadable(attachment.getSize()), crc, attachment.getBytes());

            if (mimeType != null && mimeType.split("/")[0].equalsIgnoreCase("image")) {
                try {
                    ImageInfo imageInfo = Imaging.getImageInfo(attachment.getInputStream().readAllBytes());

                    Map<String, Object> metadata = new HashMap<>();
                    metadata.put("Height In Pixels", imageInfo.getHeight());
                    metadata.put("Width In Pixels", imageInfo.getWidth());

                    attachment1.setMetadata(metadata);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Log.trace("Attempts to upload a new attachment {}", attachment1);
            attachmentMapper.save(attachment1);

        } catch (Exception e) {
            throw new RuntimeException(attachment.getName() + " could not be saved.", e);
        }
    }

    public AttachmentResponse get(UUID id) {
        Log.trace("Requests an attachment with ID{}", id);
        return attachmentMapper.get(id);
    }

    public List<AttachmentMetadata> getALl() {
        Log.trace("Requests all metadata available for every attachment");
        return attachmentMapper.getAll();
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
