package com.globallogic.amcr.attachmentcomponent.persistence.model;

import java.util.Objects;
import java.util.UUID;

public class UploadFileResponse {
    private UUID id;
    private String fileName;
    private String fileDownloadUri;
    private String filetype;
    private long size;

    public UploadFileResponse() {

    }

    public UploadFileResponse(UUID id, String fileName, String fileDownloadUri, String filetype, long size) {
        this.id = id;
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.filetype = filetype;
        this.size = size;
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

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UploadFileResponse that = (UploadFileResponse) o;
        return size == that.size && Objects.equals(id, that.id) && Objects.equals(fileName, that.fileName) && Objects.equals(fileDownloadUri, that.fileDownloadUri) && Objects.equals(filetype, that.filetype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileName, fileDownloadUri, filetype, size);
    }

    @Override
    public String toString() {
        return "UploadFileResponse{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileDownloadUri='" + fileDownloadUri + '\'' +
                ", filetype='" + filetype + '\'' +
                ", size=" + size +
                '}';
    }
}
