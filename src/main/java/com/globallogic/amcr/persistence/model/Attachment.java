package com.globallogic.amcr.persistence.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Attachment {
    private UUID id;
    private String fileName;
    private String fileType;
    private String fileSize;
    private byte[] data;
    private String downloadUri;
    private UUID feedbackId;

    public Attachment(UUID id, String fileName, String fileType, String fileSize, byte[] data, String downloadUri, UUID feedbackId) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.data = data;
        this.downloadUri = downloadUri;
        this.feedbackId = feedbackId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public UUID getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(UUID feedbackId) {
        this.feedbackId = feedbackId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return getId().equals(that.getId()) && getFileName().equals(that.getFileName()) && getFileType().equals(that.getFileType()) && getFileSize().equals(that.getFileSize()) && Arrays.equals(getData(), that.getData()) && getDownloadUri().equals(that.getDownloadUri()) && getFeedbackId().equals(that.getFeedbackId());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getFileName(), getFileType(), getFileSize(), getDownloadUri(), getFeedbackId());
        result = 31 * result + Arrays.hashCode(getData());
        return result;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", data=" + Arrays.toString(data) +
                ", downloadUri='" + downloadUri + '\'' +
                ", feedbackId=" + feedbackId +
                '}';
    }
}
