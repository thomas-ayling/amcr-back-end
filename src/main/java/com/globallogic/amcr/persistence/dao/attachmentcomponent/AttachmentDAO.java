package com.globallogic.amcr.persistence.dao.attachmentcomponent;

import com.globallogic.amcr.persistence.mapper.attachmentcomponent.AttachmentMapper;
import com.globallogic.amcr.persistence.model.attachmentcomponent.Attachment;
import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentMetadata;
import com.globallogic.amcr.persistence.payload.attachmentcomponent.AttachmentResponse;
import com.globallogic.amcr.utils.Assert;
import com.globallogic.amcr.utils.ByteConverter;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.Imaging;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.CRC32C;
import java.util.zip.Checksum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class AttachmentDAO implements DAO<MultipartFile, AttachmentResponse> {
    public final Logger Log = LoggerFactory.getLogger(AttachmentDAO.class.getName());
    private final AttachmentMapper attachmentMapper;

    public AttachmentDAO(AttachmentMapper attachmentMapper) {
        this.attachmentMapper = Assert.assertNull(attachmentMapper, "Attachment mapper cannot be null");
    }

    public void upload(MultipartFile attachment) {
        try {
            /**
             * Gets the attachment name.
             */
            String attachmentName = StringUtils.cleanPath(Objects.requireNonNull(attachment.getOriginalFilename()));

            /**
             * Generates a UUID for the attachment.
             */
            UUID id = UUID.randomUUID();

            /**
             * Generates a CRC32 for the attachment.
             */
            byte[] data1 = attachment.getBytes();
            Checksum crc32c = new CRC32C();
            crc32c.update(data1);
            long crc = crc32c.getValue();

            /**
             * Generates and sets a URI for the attachment.
             */
            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/attachments/retrieve/")
                    .path(id.toString())
                    .toUriString();

            File file = new File(attachmentName);
            String mimeType = Files.probeContentType(file.toPath());

            /**
             * Creates a new instance of Attachment.
             */
            Attachment attachment1 = new Attachment(id, attachmentName, downloadUri, attachment.getContentType(),
                    ByteConverter.bytesToReadable(attachment.getSize()), crc, attachment.getBytes());

            /**
             * Checks whether the attachment is an "image/*", if it is it proceeds to generate metadata for the image.
             */
            if (mimeType != null && mimeType.split("/")[0].equalsIgnoreCase("image")) {
                try {
                    ImageInfo imageInfo = Imaging.getImageInfo(attachment.getInputStream().readAllBytes());
                    int height = imageInfo.getHeight();
                    int width = imageInfo.getWidth();
                    int bitsPerPixel = imageInfo.getBitsPerPixel();
                    int numberOfImages = imageInfo.getNumberOfImages();
                    int physicalHeightDpi = imageInfo.getPhysicalHeightDpi();
                    int physicalWidthDpi = imageInfo.getPhysicalWidthDpi();
                    String formatDetails = imageInfo.getFormatDetails();
                    ImageInfo.ColorType colorType = imageInfo.getColorType();
                    List<String> comments = imageInfo.getComments();
                    float heightInInches = imageInfo.getPhysicalHeightInch();
                    float widthInInches = imageInfo.getPhysicalWidthInch();
                    ImageInfo.CompressionAlgorithm compressionAlgorithm = imageInfo.getCompressionAlgorithm();

                    Map<Object, Object> metadata = Stream.of(new Object[][] {
                            {"Height In Pixels", height },
                            {"Width In Pixels", width },
                            {"Bits Per Pixel", bitsPerPixel },
                            {"Number Of Images", numberOfImages },
                            {"Physical Height DPI", physicalHeightDpi },
                            {"Physical Width DPI", physicalWidthDpi },
                            {"Format Details", formatDetails },
                            {"Colour Type", colorType },
                            { "Comments", comments },
                            { "Height In Inches", heightInInches },
                            { "Width In Inches", widthInInches},
                            { "Compression Algorithm", compressionAlgorithm}
                    }).collect(Collectors.toMap(data -> data[0], data ->data[1]));

                    attachment1.setMetadata(metadata);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Log.trace("Attempts to upload a new attachment {}", attachment1);
            attachmentMapper.insert(attachment1);

        } catch (Exception e) {
            throw new RuntimeException(attachment.getName() + " could not be saved.", e);
        }
    }

    public AttachmentResponse retrieve(UUID id) {
        Log.trace("Requests an attachment with ID{}", id);
        return attachmentMapper.retrieve(id);
    }

    public List<AttachmentMetadata> retrieveAll() {
        Log.trace("Requests all metadata available for every attachment");
        return attachmentMapper.retrieveAll();
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
