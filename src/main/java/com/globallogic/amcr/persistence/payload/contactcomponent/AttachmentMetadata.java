package com.globallogic.amcr.persistence.payload.contactcomponent;

/**
 * File response for email attachment download link
 */
public class AttachmentMetadata {
    private String fileName;
    private long fileSize;
    private String downloadUri;

    /**
     * @param fileName    the name of the file
     * @param fileSize    the size of the file in bytes, converted to a readable format when added to the email
     * @param downloadUri the uri for the file download
     */

    public AttachmentMetadata(String fileName, long fileSize, String downloadUri) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.downloadUri = downloadUri;
    }

    public AttachmentMetadata() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }
}
