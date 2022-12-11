package com.globallogic.amcr.payload;

import org.apache.ibatis.annotations.Select;

public class AttachmentMetadata {
    private String fileName;
    private String fileSize;
    private String downloadUri;

    public AttachmentMetadata() {
    }

    public AttachmentMetadata(String fileName, String fileSize, String downloadUri) {
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

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }
}
