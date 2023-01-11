package com.globallogic.amcr.persistence.model.contactcomponent;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Attachment model for sending attachments to the database
 */

public class Attachment {
    private UUID id;
    private String fileName;
    private String fileType;
    private long fileSize;
    private byte[] data;
    private String downloadUri;
    private UUID feedbackId;

    /**
     * @param fileName    the name of the file being saved
     * @param fileType    the type of the file to be downloaded - received from the MultipartFile
     * @param fileSize    the size of the file in bytes - received form the MultipartFile
     * @param data        the raw binary data of the file
     */

    public Attachment(String fileName, String fileType, long fileSize, byte[] data) {
        Objects.requireNonNull(fileName, "File name cannot be null");
        Objects.requireNonNull(fileType, "File type cannot be null");
        Objects.requireNonNull(data, "Data cannot be null");
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.data = data;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        Objects.requireNonNull(id, "ID cannot be null");
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        Objects.requireNonNull(fileName, "File name cannot be null");
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        Objects.requireNonNull(fileType, "File type cannot be null");
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        Objects.requireNonNull(data, "Data cannot be null");
        this.data = data;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        Objects.requireNonNull(downloadUri, "Download uri cannot be null");
        this.downloadUri = downloadUri;
    }

    public UUID getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(UUID feedbackId) {
        Objects.requireNonNull(feedbackId, "Feedback id cannot be null");
        this.feedbackId = feedbackId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return getFileSize() == that.getFileSize() && getId().equals(that.getId()) && getFileName().equals(that.getFileName()) && getFileType().equals(that.getFileType()) && Arrays.equals(getData(), that.getData()) && getDownloadUri().equals(that.getDownloadUri()) && getFeedbackId().equals(that.getFeedbackId());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getFileName(), getFileType(), getFileSize(), getDownloadUri(), getFeedbackId());
        result = 31 * result + Arrays.hashCode(getData());
        return result;
    }

    @Override
    public String toString() {
        return "Attachment{" + "id=" + id + ", fileName='" + fileName + '\'' + ", fileType='" + fileType + '\'' + ", fileSize='" + fileSize + '\'' + ", data=" + Arrays.toString(data) + ", downloadUri='" + downloadUri + '\'' + ", feedbackId=" + feedbackId + '}';
    }
}
