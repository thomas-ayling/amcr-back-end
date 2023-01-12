package com.globallogic.amcr.attachment_component.persistance.dao;

import com.globallogic.amcr.attachment_component.mapper.AttachmentMapper;
import com.globallogic.amcr.attachment_component.persistance.model.Attachment;
import com.globallogic.amcr.attachment_component.persistance.payload.AttachmentMetadata;
import com.globallogic.amcr.attachment_component.persistance.payload.AttachmentResponse;
import com.globallogic.amcr.attachment_component.utils.ByteConverter;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.Imaging;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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

    private final AttachmentMapper attachmentMapper;

    public AttachmentDAO(AttachmentMapper attachmentMapper) {
        this.attachmentMapper = attachmentMapper;
    }

    public void upload(MultipartFile attachment) {
        try {
            String attachmentName = StringUtils.cleanPath(Objects.requireNonNull(attachment.getOriginalFilename()));

            UUID id = UUID.randomUUID();

            byte[] data = attachment.getBytes();
            Checksum crc32c = new CRC32C();
            crc32c.update(data);
            long crc = crc32c.getValue();

            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/attachments/retrieve/")
                    .path(id.toString())
                    .toUriString();

            File file = new File(attachmentName);
            String mimeType = Files.probeContentType(file.toPath());

            String imageMetadata = null;
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

//                    Map<String, Object> metadataData = new HashMap<>();
//                    metadataData.put("Height In Pixels", height);
//                    metadataData.put("Width In Pixels", width);
//                    metadataData.put("Bits Per Pixel", bitsPerPixel);
//                    metadataData.put("Number Of Images", numberOfImages);
//                    metadataData.put("Physical Height DPI", physicalHeightDpi);
//                    metadataData.put("Physical Width DPI", physicalWidthDpi);
//                    metadataData.put("Format Details", formatDetails);
//                    metadataData.put("Colour Type", colorType);
//                    metadataData.put("Comments", comments);
//                    metadataData.put("Height In Inches", heightInInches);
//                    metadataData.put("Width In Inches", widthInInches);
//                    metadataData.put("Compression Algorithm", compressionAlgorithm);

                    imageMetadata = new JSONObject()
                            .put("Height In Pixels", height)
                            .put("Width In Pixels", width)
                            .put("Bits Per Pixel", bitsPerPixel)
                            .put("Number Of Images", numberOfImages)
                            .put("Physical Height DPI", physicalHeightDpi)
                            .put("Physical Width DPI", physicalWidthDpi)
                            .put("Format Details", formatDetails)
                            .put("Colour Type", colorType)
                            .put("Comments", comments)
                            .put("Height In Inches", heightInInches)
                            .put("Width In Inches", widthInInches)
                            .put("Compression Algorithm", compressionAlgorithm)
                            .toString();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Attachment attachment1 = new Attachment(id, attachmentName, downloadUri, attachment.getContentType(),
                    ByteConverter.humanReadableByteCountSI(attachment.getSize()), crc, imageMetadata, attachment.getBytes());
            attachmentMapper.insert(attachment1);

        } catch (Exception e) {
            throw new RuntimeException(attachment.getName() + " could not be saved.", e);
        }
    }

    public AttachmentResponse retrieve(UUID id) {
        return attachmentMapper.retrieve(id);
    }

    public List<AttachmentMetadata> retrieveAll() {
        return attachmentMapper.retrieveAll();
    }

    public void delete(UUID id) {
        try {
            attachmentMapper.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("DAO could not delete attachment with ID " + id, e);
        }
    }
}
