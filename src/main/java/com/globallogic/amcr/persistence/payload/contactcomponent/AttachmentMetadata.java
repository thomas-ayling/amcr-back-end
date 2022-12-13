package com.globallogic.amcr.persistence.payload.contactcomponent;
public class AttachmentMetadata {
    private String fileName;
    private long fileSize;
    private String downloadUri;

    public AttachmentMetadata() {
    }

    public AttachmentMetadata(String fileName, long fileSize, String downloadUri) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.downloadUri = downloadUri;
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
