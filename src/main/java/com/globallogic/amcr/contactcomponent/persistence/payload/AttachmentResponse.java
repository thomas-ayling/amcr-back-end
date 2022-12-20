package com.globallogic.amcr.contactcomponent.persistence.payload;

/**
 * Attachment response to download a file from the files database
 */
public class AttachmentResponse {
    private String fileName;
    private String fileType;
    private byte[] data;

    /**
     * @param fileName the name of the file
     * @param fileType the type of the file
     * @param data     the raw binary data of the file to be downloaded
     */

    public AttachmentResponse(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public AttachmentResponse() {
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
