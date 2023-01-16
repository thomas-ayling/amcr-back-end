package com.globallogic.amcr.persistence.payload.contactcomponent;

import com.globallogic.amcr.utils.Assert;
import jakarta.validation.constraints.NotNull;

/**
 * File response for email attachment download link
 */
public class AttachmentMetadata {
    @NotNull
    private String fileName;
    @NotNull
    private long fileSize;
    @NotNull
    private String downloadUri;

    /**
     * @param fileName    the name of the file
     * @param fileSize    the size of the file in bytes, converted to a readable format when added to the email
     * @param downloadUri the uri for the file download
     */

    public AttachmentMetadata(String fileName, long fileSize, String downloadUri) {
        this.fileName = Assert.assertNotNull(fileName, "File name cannot be null");
        this.fileSize = Assert.assertNotNull(fileSize, "File size cannot be null");
        this.downloadUri = Assert.assertNotNull(downloadUri, "Download URI cannot be null");
    }

    public AttachmentMetadata() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = Assert.assertNotNull(fileName, "File name cannot be null");
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = Assert.assertNotNull(fileSize, "File size cannot be null");
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = Assert.assertNotNull(downloadUri, "Download URI cannot be null");
    }
}
